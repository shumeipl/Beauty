package com.pengling.beauty.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Sms {
    String phoneNumber;
    String code;
    int min;
}
