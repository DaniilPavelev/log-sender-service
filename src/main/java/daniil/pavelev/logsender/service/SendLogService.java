package daniil.pavelev.logsender.service;

import daniil.pavelev.logsender.annotation.SendLog;
import org.springframework.stereotype.Service;


@Service
@SendLog
public class SendLogService implements SendLogInter {

    @Override
    public String doSomething(){
        return "something already done";
    }

}
