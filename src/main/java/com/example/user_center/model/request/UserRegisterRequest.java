package com.example.user_center.model.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * author: Victor;
 * version: 1.0
 */
@Data
public class UserRegisterRequest implements Serializable {


    @Serial
    private static final long serialVersionUID = -5731895751922816239L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;

    private String  studentId;
}
