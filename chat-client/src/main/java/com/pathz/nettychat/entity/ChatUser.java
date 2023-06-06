package com.pathz.nettychat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.net.InetAddress;

@Data
@AllArgsConstructor
public class ChatUser {
    private final String username;
    private final InetAddress address;
}
