create table MEMBER
(
    MEMBER_ID         bigint auto_increment
        primary key,
    CREATED_AT        datetime(6)  not null,
    UPDATED_AT        datetime(6)  not null,
    BLOG_URL          varchar(100) null,
    CAREER            varchar(20)  not null,
    EMAIL             varchar(40)  not null,
    FIELD             varchar(20)  not null,
    GITHUB_URL        varchar(100) null,
    IS_DELETED        bit          not null,
    MBTI              varchar(5)   not null,
    MEMBER_ROLE       varchar(16)  not null,
    NICK_NAME         varchar(30)  not null,
    PROFILE_IMAGE_URL varchar(150) null,
    constraint UK_c619bafwe6254fnnmnb83mf54
        unique (EMAIL),
    constraint UK_tb4f7a4tx2f2ut4699qb2fwey
        unique (NICK_NAME)
);

create table FEED
(
    FEED_ID    bigint auto_increment
        primary key,
    CREATED_AT datetime(6)   not null,
    UPDATED_AT datetime(6)   not null,
    CREATED_BY bigint        not null,
    UPDATED_BY bigint        not null,
    CONTENTS   varchar(1000) not null,
    MEMBER_ID  bigint        null,
    constraint FKiknp89x9qe6kyp8dycp9ooud4
        foreign key (MEMBER_ID) references MEMBER (MEMBER_ID)
);

create table COMMENT
(
    COMMENT_ID bigint auto_increment
        primary key,
    CREATED_AT datetime(6)  not null,
    UPDATED_AT datetime(6)  not null,
    CREATED_BY bigint       not null,
    UPDATED_BY bigint       not null,
    CONTENTS   varchar(500) not null,
    FEED_ID    bigint       null,
    MEMBER_ID  bigint       null,
    PARENT_ID  bigint       null,
    constraint FK980qe8nos9rmeb0c5fo8vuy03
        foreign key (PARENT_ID) references COMMENT (COMMENT_ID),
    constraint FKc4npwilsaafdqww5ysicil90l
        foreign key (FEED_ID) references FEED (FEED_ID),
    constraint FKtpb6m6vd5l9eeihx606s41hup
        foreign key (MEMBER_ID) references MEMBER (MEMBER_ID)
);

create table FEED_IMAGE
(
    FEED_IMAGE_ID bigint auto_increment
        primary key,
    CREATED_AT    datetime(6)  not null,
    UPDATED_AT    datetime(6)  not null,
    CREATED_BY    bigint       not null,
    UPDATED_BY    bigint       not null,
    IMAGE_URL     varchar(150) null,
    FEED_ID       bigint       null,
    constraint UK_f28nyjstp6lj6ebxcacwwhbxl
        unique (IMAGE_URL),
    constraint FK1yctuh3cv38qv0hba929qkf73
        foreign key (FEED_ID) references FEED (FEED_ID)
);

create table FEED_LIKE
(
    FEED_LIKE_ID bigint auto_increment
        primary key,
    CREATED_AT   datetime(6) not null,
    UPDATED_AT   datetime(6) not null,
    CREATED_BY   bigint      not null,
    UPDATED_BY   bigint      not null,
    FEED_ID      bigint      null,
    MEMBER_ID    bigint      null,
    constraint FKl5eo7lgei7rbexc13guj3lagx
        foreign key (MEMBER_ID) references MEMBER (MEMBER_ID),
    constraint FKsn032jlgjajciie2cfm1dcawg
        foreign key (FEED_ID) references FEED (FEED_ID)
);

create table STUDY_GROUP
(
    STUDY_GROUP_ID     bigint auto_increment
        primary key,
    CREATED_AT         datetime(6)   not null,
    UPDATED_AT         datetime(6)   not null,
    CREATED_BY         bigint        not null,
    UPDATED_BY         bigint        not null,
    DESCRIPTION        varchar(1000) not null,
    IMAGE_URL          varchar(150)  null,
    IS_DELETED         bit           not null,
    IS_ONLINE          bit           not null,
    NUMBER_OF_MEMBERS  int           not null,
    NUMBER_OF_RECRUITS int           not null,
    REGION             varchar(16)   null,
    END_DATE_TIME      datetime(6)   not null,
    START_DATE_TIME    datetime(6)   not null,
    THUMBNAIL_URL      varchar(150)  null,
    TITLE              varchar(100)  not null,
    TOPIC              varchar(20)   not null
);

create table PREFERRED_MBTIS
(
    STUDY_GROUP_ID  bigint       not null,
    PREFERRED_MBTIS varchar(255) not null,
    primary key (STUDY_GROUP_ID, PREFERRED_MBTIS),
    constraint FK4qus627i5nq8l1c54g5kfmvik
        foreign key (STUDY_GROUP_ID) references STUDY_GROUP (STUDY_GROUP_ID)
);

create table STUDY_GROUP_MEMBER
(
    STUDY_GROUP_MEMBER_ID   bigint auto_increment
        primary key,
    CREATED_AT              datetime(6) not null,
    UPDATED_AT              datetime(6) not null,
    CREATED_BY              bigint      not null,
    UPDATED_BY              bigint      not null,
    STUDY_GROUP_MEMBER_ROLE varchar(16) not null,
    MEMBER_ID               bigint      null,
    STUDY_GROUP_ID          bigint      null,
    constraint FK89w0nm3i3vqke3at3f10l58m6
        foreign key (STUDY_GROUP_ID) references STUDY_GROUP (STUDY_GROUP_ID),
    constraint FKgujt6hpyjo0ec5twx2ts0e3ks
        foreign key (MEMBER_ID) references MEMBER (MEMBER_ID)
);

create table STUDY_GROUP_QUESTION
(
    STUDY_GROUP_QUESTION_ID bigint auto_increment
        primary key,
    CREATED_AT              datetime(6)  not null,
    UPDATED_AT              datetime(6)  not null,
    CREATED_BY              bigint       not null,
    UPDATED_BY              bigint       not null,
    CONTENTS                varchar(500) not null,
    MEMBER_ID               bigint       null,
    PARENT_ID               bigint       null,
    STUDY_GROUP_ID          bigint       null,
    constraint FK3ct2c96n5na4i7l5d9k4r9t02
        foreign key (STUDY_GROUP_ID) references STUDY_GROUP (STUDY_GROUP_ID),
    constraint FK5x6qchqtthkcjw1op78cpge8v
        foreign key (MEMBER_ID) references MEMBER (MEMBER_ID),
    constraint FKs5ekehbrprf859sn01qx7shbu
        foreign key (PARENT_ID) references STUDY_GROUP_QUESTION (STUDY_GROUP_QUESTION_ID)
);