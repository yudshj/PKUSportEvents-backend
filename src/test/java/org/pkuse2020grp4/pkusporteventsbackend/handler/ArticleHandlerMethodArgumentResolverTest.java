package org.pkuse2020grp4.pkusporteventsbackend.handler;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.pkuse2020grp4.pkusporteventsbackend.entity.Tag;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArticleHandlerMethodArgumentResolverTest {

    @Test
    void resolveArgument() {
        List<Tag> list = new ArrayList<Tag>();
        Tag tag1 = new Tag();
        tag1.setName("tag name 1");
        tag1.setTagId(1);
        list.add(tag1);
        Tag tag2 = new Tag();
        tag2.setName("tag name 2");
        tag2.setTagId(2);
        list.add(tag2);

        String ser = JSON.toJSONString(list);
        List<Tag> result = JSON.parseArray(ser, Tag.class);
        assertArrayEquals(list.toArray(new Tag[0]), result.toArray(new Tag[0]));
    }
}