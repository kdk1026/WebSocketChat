package com.kdk.app.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

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
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

}
