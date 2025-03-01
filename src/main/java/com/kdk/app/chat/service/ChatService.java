package com.kdk.app.chat.service;

import java.util.List;

import com.kdk.app.jpa.entity.ChatRoom;
import com.kdk.app.jpa.entity.User;

/**
 * <pre>
 * -----------------------------------
 * 개정이력
 * -----------------------------------
 * 2025. 3. 1. kdk	최초작성
 * </pre>
 *
 *
 * @author kdk
 */
public interface ChatService {

	public List<ChatRoom> findAllChatRooms();

	public ChatRoom createChatRoom(String name);

	public List<User> findAllUsers();

	public User createUser(String username);

	public void addUserToChatRoom(Long chatRoomId, String username);

}
