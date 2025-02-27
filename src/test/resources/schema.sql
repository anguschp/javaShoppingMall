CREATE TABLE if not exists `products` (
                            `product_id` int NOT NULL AUTO_INCREMENT,
                            `product_name` varchar(200) NOT NULL,
                            `category` varchar(100) NOT NULL,
                            `image_url` varchar(256) NOT NULL,
                            `price` int NOT NULL,
                            `stock` int NOT NULL,
                            `product_desc` varchar(1024) DEFAULT NULL,
                            `created_date` timestamp(6) NOT NULL,
                            `last_modified_date` timestamp(6) NOT NULL,
                            PRIMARY KEY (`product_id`)
);
