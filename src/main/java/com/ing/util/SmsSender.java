package com.ing.util;

import com.ing.dto.SmsRequest;

public interface SmsSender {

    void sendSms(SmsRequest smsRequest);
}
