package com.kdk.app.chat.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdk.app.chat.service.ChatService;
import com.kdk.app.jpa.entity.ChatRoom;
import com.kdk.app.jpa.entity.User;
import com.kdk.app.jpa.repository.ChatRoomRepository;
import com.kdk.app.jpa.repository.UserRepository;

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
@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private UserRepository userRepository;

	@Override
	public List<ChatRoom> findAllChatRooms() {
		return chatRoomRepository.findAll();
	}

	@Override
	public ChatRoom createChatRoom(String name) {
		ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName(name);
        return chatRoomRepository.save(chatRoom);
	}

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User createUser(String username) {
		User user = new User();
        user.setUsername(username);
        return userRepository.save(user);
	}

	@Override
	public void addUserToChatRoom(Long chatRoomId, String username) {
        Optional<ChatRoom> chatRoomOptional = chatRoomRepository.findById(chatRoomId);

        if ( chatRoomOptional.isPresent() ) {
            ChatRoom chatRoom = chatRoomOptional.get();
            chatRoom.getUsers().add(username);
            chatRoomRepository.save(chatRoom);
        } else {
            throw new RuntimeException("ChatRoom not found");
        }

	}

}
