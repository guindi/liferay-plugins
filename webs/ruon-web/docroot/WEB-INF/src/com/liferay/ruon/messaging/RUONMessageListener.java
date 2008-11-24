/**
* Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in
* all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*/

/**
* The contents of this file are subject to the terms of the Common Development
* and Distribution License (the License). You may not use this file except in
* compliance with the License.
*
* You can obtain a copy of the License at http://www.sun.com/cddl/cddl.html and
* legal/CDDLv1.0.txt. See the License for the specific language governing
* permission and limitations under the License.
*
* When distributing Covered Code, include this CDDL Header Notice in each file
* and include the License file at legal/CDDLv1.0.txt.
*
* If applicable, add the following below the CDDL Header, with the fields
* enclosed by brackets [] replaced by your own identifying information:
* "Portions Copyrighted [year] [name of copyright owner]"
*
* Copyright 2008 Sun Microsystems Inc. All rights reserved.
*/

package com.liferay.ruon.messaging;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.util.Converter;
import com.liferay.ruon.model.Network;
import com.liferay.ruon.model.Presence;
import com.liferay.ruon.service.NetworkLocalServiceUtil;
import com.liferay.ruon.service.PresenceLocalServiceUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="RUONMessageListener.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class RUONMessageListener implements MessageListener {

	public void receive(Message message) {
		try {
			doReceive(message);
		}
		catch (Exception e) {
			_log.error("Unable to process message " + message, e);
		}
	}

	protected void doReceive(Message message) throws Exception {
		String command = message.getString("command");

		if (command.equals("getPresences")) {
			getPresences(message);
		}
		else if (command.equals("updateNetwork")) {
			updateNetwork(message);
		}
		else if (command.equals("updatePresence")) {
			updatePresence(message);
		}else if (command.equalsIgnoreCase("delegateToNetwork")){
			delegateToNetwork(message);
		}
	}

	protected void delegateToNetwork(Message message) throws Exception{
		String name = message.getString("name");
		Network network = NetworkLocalServiceUtil.getNetwork(name);
		Converter converter = _converters.get(network.getNetworkId());

		Map<String, Object> input = new HashMap<String, Object>();
		input.put("command","delegateToNetwork");
		input.put("message",message);

		Object output = converter.convert(input);
		JSONObject payLoadJSON = JSONFactoryUtil.createJSONObject();

		if(output != null && ((String)output).equalsIgnoreCase("success")){
			payLoadJSON.put("output","success");
		}
		else{
			payLoadJSON.put("output","failure");
		}

		message.setPayload(payLoadJSON);
		MessageBusUtil.sendMessage(message.getResponseDestination(), message);
	}

	protected void getPresences(Message message) throws Exception {
		long userId = message.getLong("userId");
		boolean online = true;
		Locale locale = (Locale)message.get("locale");

		List<Presence> presences = PresenceLocalServiceUtil.getPresences(
			userId, online);

		JSONObject payloadJSON = JSONFactoryUtil.createJSONObject();

		JSONArray presencesJSON = JSONFactoryUtil.createJSONArray();

		payloadJSON.put("presencesJSON", presencesJSON);

		for (Presence presence : presences) {
			Converter converter = _converters.get(presence.getNetworkId());

			if (converter == null) {
				continue;
			}

			Map<String, Object> input = new HashMap<String, Object>();
			input.put("command","presenceOutput");
			input.put("userId", userId);
			input.put("online", online);
			input.put("locale", locale);

			Object output = converter.convert(input);

			JSONObject presenceJSON = JSONFactoryUtil.createJSONObject();

			presenceJSON.put("userId", userId);
			presenceJSON.put("online", online);
			presenceJSON.put("output", String.valueOf(output));

			presencesJSON.put(presenceJSON);
		}

		message.setPayload(payloadJSON);

		MessageBusUtil.sendMessage(message.getResponseDestination(), message);
	}

	protected void updateNetwork(Message message) throws Exception {
		String name = message.getString("name");
		int ttl = message.getInteger("ttl");
		Converter converter = (Converter)message.get("converter");

		Network network = NetworkLocalServiceUtil.updateNetwork(name, ttl);

		_converters.put(network.getNetworkId(), converter);
	}

	protected void updatePresence(Message message) throws Exception {
		long userId = message.getLong("userId");
		String networkName = message.getString("networkName");
		boolean online = message.getBoolean("online");

		PresenceLocalServiceUtil.updatePresence(userId, networkName, online);
	}

	private static Log _log = LogFactory.getLog(RUONMessageListener.class);

	private static Map<Long, Converter> _converters =
		new ConcurrentHashMap<Long, Converter>();

}