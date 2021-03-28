CREATE TABLE `users1` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `first_name` varchar(255),
  `last_name` varchar(255),
  `middle_name` varchar(255),
  `full_name` varchar(255),
  `gmail` varchar(255),
  `pass` varchar(255),
  `created_at` timestamp
);

CREATE TABLE `friendship` (
  `f_id` int AUTO_INCREMENT,
  `p1_id` int,
  `p2_id` int,
  `f_time` timestamp
);

CREATE TABLE `Post` (
  `person_id` int,
  `post_id` int PRIMARY KEY AUTO_INCREMENT,
  `post_name` varchar(255),
  `post_type` int,
  `post_data_type` varchar(255),
  `post_timestaamp` timestamp,
  `content` varchar(255)
);

CREATE TABLE `address` (
  `preson_id` int PRIMARY KEY,
  `house_no` int,
  `street_no` int,
  `state_code` int,
  `city_code` int
);

CREATE TABLE `state` (
  `state_code` int,
  `state_name` varchar(255),
  `country_code` int,
  `coordinates` int
);

CREATE TABLE `country` (
  `country_code` int PRIMARY KEY,
  `country_name` varcha
);

CREATE TABLE `groups` (
  `group_id` int PRIMARY KEY AUTO_INCREMENT,
  `group_admin_id` int,
  `group_name` varchar(255)
);

CREATE TABLE `GroupMembers` (
  `group_id` int,
  `person_id` int
);

CREATE TABLE `group_posts` (
  `group_id` int,
  `post_id` int
);

CREATE TABLE `interaction` (
  `person_id` int,
  `post_id` int,
  `comment_id` int,
  `interaction_type` int,
  `interaction_id` int PRIMARY KEY AUTO_INCREMENT
);

CREATE TABLE `comment` (
  `comment_data` varchar(255),
  `interaction_id` int,
  `comment_type` int,
  `comment_id` int PRIMARY KEY AUTO_INCREMENT
);

ALTER TABLE `friendship` ADD FOREIGN KEY (`p1_id`) REFERENCES `users1` (`id`);

ALTER TABLE `friendship` ADD FOREIGN KEY (`p2_id`) REFERENCES `users1` (`id`);

ALTER TABLE `Post` ADD FOREIGN KEY (`person_id`) REFERENCES `users1` (`id`);

ALTER TABLE `address` ADD FOREIGN KEY (`preson_id`) REFERENCES `users1` (`id`);

ALTER TABLE `groups` ADD FOREIGN KEY (`group_admin_id`) REFERENCES `users1` (`id`);

ALTER TABLE `GroupMembers` ADD FOREIGN KEY (`group_id`) REFERENCES `groups` (`group_id`);

ALTER TABLE `GroupMembers` ADD FOREIGN KEY (`person_id`) REFERENCES `users1` (`id`);

ALTER TABLE `group_posts` ADD FOREIGN KEY (`group_id`) REFERENCES `groups` (`group_id`);

ALTER TABLE `group_posts` ADD FOREIGN KEY (`post_id`) REFERENCES `Post` (`post_id`);

ALTER TABLE `interaction` ADD FOREIGN KEY (`post_id`) REFERENCES `Post` (`post_id`);

ALTER TABLE `comment` ADD FOREIGN KEY (`interaction_id`) REFERENCES `interaction` (`interaction_id`);
