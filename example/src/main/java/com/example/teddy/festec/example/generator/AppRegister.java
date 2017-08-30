 package com.example.teddy.festec.example.generator;


 import com.example.annotations.AppRegisterGenerator;
 import com.example.latte.wechat.templates.AppRegisterTemplate;

 /**
 * Created by 傅令杰 on 2017/4/22
 */
@SuppressWarnings("unused")
@AppRegisterGenerator(
        packageName = "com.example.teddy.festec.example",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {
}
