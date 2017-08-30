package com.example.teddy.festec.example.generator;


import com.example.annotations.EntryGenerator;
import com.example.latte.wechat.templates.WXEntryTemplate;

/**
 * Created by 傅令杰 on 2017/4/22
 */

@SuppressWarnings("unused")
@EntryGenerator(
        packageName = "com.example.teddy.festec.example",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}
