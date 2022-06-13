create sequence hibernate_sequence start 1 increment 1;
    create table orders (
       order_id  bigserial not null,
        description varchar(2000),
        order_state varchar(255),
        title varchar(150) not null,
        primary key (order_id)
    );

    create table orders_order_products (
       orders_order_id int8 not null,
        order_products_product_id int8 not null,
        primary key (orders_order_id, order_products_product_id)
    );

    create table products (
       product_id int8 not null,
        description varchar(2000),
        state varchar(255) not null,
        title varchar(150) not null,
        primary key (product_id)
    );

    alter table orders_order_products 
       drop constraint UK_3s0g7nvd090ysfjrf5n3rrge;

    alter table orders_order_products 
       add constraint UK_3s0g7nvd090ysfjrf5n3rrge unique (order_products_product_id);


    alter table orders_order_products 
       add constraint FKaw7c7hf9hgyyr6r0c8gqfodmi 
       foreign key (order_products_product_id) 
       references products;

    alter table orders_order_products 
       add constraint FK23t6m5ejg8omwan9i20lc42ls 
       foreign key (orders_order_id) 
       references orders;
