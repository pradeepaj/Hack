package com.ing.service;

import com.ing.dto.SmsRequest;

public interface SmsSender {

    void sendSms(SmsRequest smsRequest);
}
