package com.example.aacapplication.data.entity;

import com.google.gson.annotations.SerializedName;

public class Post {

    /*
    "node": {
        "avatar_large": "//cdn.v2ex.com/navatar/c20a/d4d7/12_large.png?m=1551072319",
        "name": "qna",
        "avatar_normal": "//cdn.v2ex.com/navatar/c20a/d4d7/12_normal.png?m=1551072319",
        "title": "问与答",
        "url": "https://www.v2ex.com/go/qna",
        "topics": 126691,
        "footer": "",
        "header": "一个更好的世界需要你持续地提出好问题。",
        "title_alternative": "Questions and Answers",
        "avatar_mini": "//cdn.v2ex.com/navatar/c20a/d4d7/12_mini.png?m=1551072319",
        "stars": 2314,
        "root": false,
        "id": 12,
        "parent_node_name": "v2ex"
    },
    "member": {
        "username": "deasty",
        "website": null,
        "github": null,
        "psn": null,
        "avatar_normal": "//cdn.v2ex.com/gravatar/c39053fb83510da1e4844847e9df6774?s=24&d=retro",
        "bio": null,
        "url": "https://www.v2ex.com/u/deasty",
        "tagline": null,
        "twitter": null,
        "created": 1489552174,
        "avatar_large": "//cdn.v2ex.com/gravatar/c39053fb83510da1e4844847e9df6774?s=24&d=retro",
        "avatar_mini": "//cdn.v2ex.com/gravatar/c39053fb83510da1e4844847e9df6774?s=24&d=retro",
        "location": null,
        "btc": null,
        "id": 221088
    },
    "last_reply_by": "",
    "last_touched": 1551081968,
    "title": "如何恢复遨游 5 的收藏夹？",
    "url": "https://www.v2ex.com/t/538529",
    "created": 1551082148,
    "content": "机器报废了，硬盘还在，提取哪些文件到新机器才能恢复以前的收藏夹？",
    "content_rendered": "机器报废了，硬盘还在，提取哪些文件到新机器才能恢复以前的收藏夹？",
    "last_modified": 1551082148,
    "replies": 0,
    "id": 538529
    */

    public int id;
    public String title;
    public String content;
    public int replies;
    public String url;
    public Node node;
    public Member member;

    public static class Node {
        public String name;
    }

    public static class Member {
        public String username;
        @SerializedName("avatar_normal")
        public String avatarNormal;
        @SerializedName("avatar_large")
        public String avatarLarge;
        @SerializedName("avatar_mini")
        public String avatarMini;
    }
}