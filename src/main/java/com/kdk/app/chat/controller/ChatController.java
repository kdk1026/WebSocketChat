package com.kdk.app.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kdk.app.chat.service.ChatService;
import com.kdk.app.chat.vo.ChatMessageVo;
import com.kdk.app.jpa.entity.ChatRoom;

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
@RestController
public class ChatController {

	@Autowired
    private ChatService chatService;

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@RequestBody ChatMessageVo chatMessage) {
        messagingTemplate.convertAndSend("/topic/" + chatMessage.getRoomId(), chatMessage);
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessageVo addUser(ChatMessageVo chatMessage) {
        chatMessage.setContent(chatMessage.getSender() + "님이 입장하셨습니다.");
        return chatMessage;
    }

    @GetMapping("/chatrooms")
    public List<ChatRoom> getChatRooms() {
        return chatService.findAllChatRooms();
    }

    @PostMapping("/chatrooms")
    public ChatRoom createChatRoom(@RequestParam String name) {
        return chatService.createChatRoom(name);
    }

    @PostMapping("/chatrooms/{id}/users")
    public void addUserToChatRoom(@PathVariable Long id, @RequestParam String username) {
        chatService.addUserToChatRoom(id, username);
    }

}
