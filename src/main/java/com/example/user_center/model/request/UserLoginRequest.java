package com.example.user_center.model.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * author: Victor;
 * version: 1.0
 */
@Data
public class UserLoginRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -8164876375753080464L;

    private String userAccount;

    private String userPassword;
}
