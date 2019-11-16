-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 15, 2019 at 03:41 PM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.1.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `condonuitydev`
--

-- --------------------------------------------------------

--
-- Table structure for table `bidding_files`
--

CREATE TABLE `bidding_files` (
  `id` int(11) NOT NULL,
  `bid_id` int(11) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `bidding_products`
--

CREATE TABLE `bidding_products` (
  `id` int(11) NOT NULL,
  `product_type` int(11) DEFAULT NULL,
  `bidding_id` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `bids`
--

CREATE TABLE `bids` (
  `id` int(11) NOT NULL,
  `vendor_org_id` int(11) DEFAULT NULL,
  `vendor_user_id` int(11) DEFAULT NULL,
  `project_id` varchar(255) DEFAULT NULL,
  `vendor_start_date` varchar(255) DEFAULT NULL,
  `vendor_end_date` varchar(255) DEFAULT NULL,
  `vendor_project_duration` varchar(255) DEFAULT NULL,
  `in_scope` varchar(255) DEFAULT NULL,
  `out_of_scope` varchar(255) DEFAULT NULL,
  `pre_requisite` varchar(255) DEFAULT NULL,
  `reason_for_choose` varchar(255) DEFAULT NULL,
  `bid_price` int(11) DEFAULT NULL,
  `is_insurance_available` tinyint(1) DEFAULT NULL,
  `insurance_id` int(11) DEFAULT NULL,
  `bid_status` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `client_association`
--

CREATE TABLE `client_association` (
  `id` int(11) NOT NULL,
  `organisation_id` int(11) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `client_user_type` int(11) DEFAULT NULL,
  `user_role` int(11) DEFAULT NULL,
  `account_verification_status` int(11) DEFAULT NULL,
  `user_account_status` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `client_org_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `client_organisation`
--

CREATE TABLE `client_organisation` (
  `client_organisation_id` int(11) NOT NULL,
  `user_type` int(11) DEFAULT NULL,
  `organisation_name` varchar(255) DEFAULT NULL,
  `management_company` varchar(255) DEFAULT NULL,
  `corporate_number` varchar(255) DEFAULT NULL,
  `registration_date` date DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `country_code` int(11) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `fax_number` varchar(255) DEFAULT NULL,
  `units` int(11) DEFAULT NULL,
  `voting_units` int(11) DEFAULT NULL,
  `manager_name` varchar(255) NOT NULL,
  `manager_email` varchar(255) NOT NULL,
  `manager_phone` varchar(255) NOT NULL,
  `board_email` varchar(255) DEFAULT 'NULL',
  `general_email` varchar(255) DEFAULT 'NULL',
  `management_email` varchar(255) DEFAULT 'NULL',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client_organisation`
--

INSERT INTO `client_organisation` (`client_organisation_id`, `user_type`, `organisation_name`, `management_company`, `corporate_number`, `registration_date`, `address`, `city`, `province`, `postal_code`, `country_code`, `phone_number`, `fax_number`, `units`, `voting_units`, `manager_name`, `manager_email`, `manager_phone`, `board_email`, `general_email`, `management_email`, `created_at`, `modified_date`) VALUES
(1, 1, 'CLIENT ORG', 'client management', 'Crp12232', '2019-11-01', 'chennai, TN', 'chennai', 'IN', '600042', 91, '1213232', 'Fx34343', 100, 50, 'Mangr', 'mg@mg.com', '87458374', 'board@gmai.com', 'general@gm.com', 'mngn@gm.com', '2019-11-15 12:09:35', '2019-11-15 12:09:35');

-- --------------------------------------------------------

--
-- Table structure for table `client_organisation_amenities`
--

CREATE TABLE `client_organisation_amenities` (
  `client_organisation_client_organisation_id` int(11) NOT NULL,
  `amenities_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `client_user`
--

CREATE TABLE `client_user` (
  `client_id` int(11) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `legal_name` varchar(255) DEFAULT NULL,
  `country_code` varchar(11) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(40) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client_user`
--

INSERT INTO `client_user` (`client_id`, `first_name`, `last_name`, `legal_name`, `country_code`, `city`, `email`, `phone`, `created_at`, `modified_date`) VALUES
(1, 'Prakash', 'M', 'PrakashExplorer', '1', 'Chennai', 'user@client.com', '1234334554', '2019-11-14 09:46:38', '2019-11-14 09:46:38'),
(6, '', '', '', NULL, '', 'Test1@clientuser.com', '', '2019-11-15 10:33:47', '2019-11-15 10:33:47'),
(7, '', '', '', NULL, '', 'Test@clientuser.com', '', '2019-11-15 10:35:31', '2019-11-15 10:35:31'),
(8, '', '', '', NULL, '', 'Test@clientuser.com', '', '2019-11-15 10:41:47', '2019-11-15 10:41:47'),
(9, '', '', '', NULL, '', 'Test2@clientuser.com', '', '2019-11-15 10:41:52', '2019-11-15 10:41:52'),
(10, '', '', '', NULL, '', 'Test2@clientuser.com', '', '2019-11-15 10:44:48', '2019-11-15 10:44:48'),
(11, '', '', '', NULL, '', 'Test3@clientuser.com', '', '2019-11-15 10:44:54', '2019-11-15 10:44:54'),
(12, '', '', '', NULL, '', 'Test4@clientuser.com', '', '2019-11-15 10:45:57', '2019-11-15 10:45:57'),
(13, '', '', '', NULL, '', 'Test5@clientuser.com', '', '2019-11-15 11:01:53', '2019-11-15 11:01:53'),
(14, '', '', '', NULL, '', 'Test5@clientuser.com', '', '2019-11-15 11:01:55', '2019-11-15 11:01:55'),
(15, '', '', '', NULL, '', 'Test6@clientuser.com', '', '2019-11-15 11:06:39', '2019-11-15 11:06:39'),
(16, '', '', '', NULL, '', 'Test7@clientuser.com', '', '2019-11-15 11:08:06', '2019-11-15 11:08:06'),
(17, '', '', '', NULL, '', 'Test7@clientuser.com', '', '2019-11-15 11:08:24', '2019-11-15 11:08:24'),
(18, '', '', '', NULL, '', 'Test8@clientuser.com', '', '2019-11-15 11:08:58', '2019-11-15 11:08:58'),
(19, '', '', '', NULL, '', 'Test9@clientuser.com', '', '2019-11-15 11:10:36', '2019-11-15 11:10:36'),
(20, '', '', '', NULL, '', 'Test9@clientuser.com', '', '2019-11-15 11:11:30', '2019-11-15 11:11:30'),
(21, '', '', '', NULL, '', 'Test11@clientuser.com', '', '2019-11-15 11:11:37', '2019-11-15 11:11:37'),
(22, '', '', '', '', '', 'Test12@clientuser.com', '', '2019-11-15 11:14:20', '2019-11-15 11:14:20');

-- --------------------------------------------------------

--
-- Table structure for table `insurance`
--

CREATE TABLE `insurance` (
  `insurance_id` int(11) NOT NULL,
  `vendor_org_id` int(11) DEFAULT NULL,
  `insurance_availability` tinyint(1) DEFAULT NULL,
  `insurance_company` varchar(255) DEFAULT NULL,
  `insurance_liability` varchar(255) DEFAULT NULL,
  `insurance_policy_expiry_date` varchar(255) DEFAULT NULL,
  `insurance_bonded` tinyint(1) DEFAULT NULL,
  `insurance_number` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `organisation_amenities_info`
--

CREATE TABLE `organisation_amenities_info` (
  `id` int(11) NOT NULL,
  `organisation_id` int(11) DEFAULT NULL,
  `amenities_name` varchar(255) DEFAULT NULL,
  `amenities_type` varchar(255) DEFAULT NULL,
  `amenities_count` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `organisation_payment_billing_details`
--

CREATE TABLE `organisation_payment_billing_details` (
  `id` int(11) NOT NULL,
  `organisation_id` int(11) DEFAULT NULL,
  `card_number` varchar(255) DEFAULT NULL,
  `name_on_card` varchar(255) DEFAULT NULL,
  `expiry_date` varchar(255) DEFAULT NULL,
  `security_code` int(11) DEFAULT NULL,
  `street_address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `predefined_tags`
--

CREATE TABLE `predefined_tags` (
  `tag_id` int(11) NOT NULL,
  `tag_name` varchar(255) DEFAULT NULL,
  `tag_status` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `projects`
--

CREATE TABLE `projects` (
  `project_id` int(11) NOT NULL,
  `client_organisation_id` int(11) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `project_modified_by` int(11) DEFAULT NULL,
  `project_name` varchar(255) DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  `bid_end_date` varchar(255) DEFAULT NULL,
  `project_start_date` varchar(255) DEFAULT NULL,
  `project_completion_deadline` varchar(255) DEFAULT NULL,
  `estimated_budget` varchar(255) DEFAULT NULL,
  `duration` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `special_conditions` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `contract_type` int(11) DEFAULT NULL,
  `insurance_required` int(10) DEFAULT NULL,
  `post_type` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `awarded_bid_id` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `projects`
--

INSERT INTO `projects` (`project_id`, `client_organisation_id`, `client_id`, `project_modified_by`, `project_name`, `tags`, `bid_end_date`, `project_start_date`, `project_completion_deadline`, `estimated_budget`, `duration`, `description`, `special_conditions`, `city`, `contract_type`, `insurance_required`, `post_type`, `status`, `awarded_bid_id`, `created_at`, `modified_date`) VALUES
(1, 1, 1, 1, 'Condonuity', 'Test', '2019-11-27 04:00:00', '2019-11-28 00:00:00', '2019-11-29 00:00:00', '1000000', '1', 'test description', 'NA', 'Chennai', 1, 1, 1, 0, 1, '2019-11-14 10:42:52', '2019-11-14 11:09:55'),
(2, 1, 1, 1, 'Condonuity', 'Test', '2019-11-27 04:00:00', '2019-11-28 00:00:00', '2019-11-29 00:00:00', '2000000', '1', 'test description', 'NA', 'Chennai', 1, 1, 1, 1, 1, '2019-11-14 10:42:52', '2019-11-14 11:10:25'),
(3, 1, 1, 1, 'Condonuity', 'Test', '2019-11-27 04:00:00', '2019-11-28 00:00:00', '2019-11-29 00:00:00', '3000000', '1', 'test description', 'NA', 'Chennai', 1, 1, 1, 2, 1, '2019-11-14 10:42:52', '2019-11-14 10:42:52');

-- --------------------------------------------------------

--
-- Table structure for table `project_files`
--

CREATE TABLE `project_files` (
  `id` int(11) NOT NULL,
  `project_id` int(11) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `project_products`
--

CREATE TABLE `project_products` (
  `id` int(11) NOT NULL,
  `project_id` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `project_questions`
--

CREATE TABLE `project_questions` (
  `id` int(11) NOT NULL,
  `project_id` int(11) DEFAULT NULL,
  `vendor_user_id` int(11) DEFAULT NULL,
  `client_user_id` int(11) DEFAULT NULL,
  `question` varchar(255) DEFAULT NULL,
  `answer` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `projectqaid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `project_questions`
--

INSERT INTO `project_questions` (`id`, `project_id`, `vendor_user_id`, `client_user_id`, `question`, `answer`, `created_at`, `modified_date`, `projectqaid`) VALUES
(1, 1, 1, 1, 'test?', 'test', '2019-11-15 13:30:01', '2019-11-15 13:30:01', 0);

-- --------------------------------------------------------

--
-- Table structure for table `project_reviews_ratings`
--

CREATE TABLE `project_reviews_ratings` (
  `id` int(11) NOT NULL,
  `client_id` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  `rating` float DEFAULT NULL,
  `review_comments` varchar(255) DEFAULT NULL,
  `reply_comments` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `project_tags`
--

CREATE TABLE `project_tags` (
  `id` int(11) NOT NULL,
  `project_id` int(11) DEFAULT NULL,
  `tag_id` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `rating_category`
--

CREATE TABLE `rating_category` (
  `id` int(11) NOT NULL,
  `rating_category` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_login`
--

CREATE TABLE `user_login` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_login`
--

INSERT INTO `user_login` (`id`, `username`, `user_id`, `user_type`, `password`, `created_at`, `modified_date`) VALUES
(1, 'user@client.com', 1, 1, '12345', '2019-11-14 09:53:43', '2019-11-14 10:03:15'),
(2, 'user@vendor.com', 1, 2, '12345', '2019-11-15 10:35:32', '2019-11-15 13:07:14'),
(8, 'Test2@clientuser.com', 0, 1, '', '2019-11-15 10:41:52', '2019-11-15 10:41:52'),
(10, 'Test3@clientuser.com', 0, 1, '', '2019-11-15 10:44:54', '2019-11-15 10:44:54'),
(11, 'Test4@clientuser.com', 0, 1, '', '2019-11-15 10:45:57', '2019-11-15 10:45:57'),
(12, 'Test5@clientuser.com', 0, 1, '', '2019-11-15 11:01:53', '2019-11-15 11:01:53'),
(14, 'Test6@clientuser.com', 15, 1, '', '2019-11-15 11:06:39', '2019-11-15 11:06:39'),
(15, 'Test11@clientuser.com', 21, 1, '', '2019-11-15 11:11:37', '2019-11-15 11:11:37'),
(16, 'Test12@clientuser.com', 22, 1, '', '2019-11-15 11:14:21', '2019-11-15 11:14:21');

-- --------------------------------------------------------

--
-- Table structure for table `user_wish_list`
--

CREATE TABLE `user_wish_list` (
  `id` int(11) NOT NULL,
  `wisher_org_id` int(11) DEFAULT NULL,
  `wisher_user_id` int(11) DEFAULT NULL,
  `favourite_org_id` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `vendor_bid`
--

CREATE TABLE `vendor_bid` (
  `bid_id` int(11) NOT NULL,
  `bid_price` varchar(255) DEFAULT NULL,
  `bid_status` int(11) NOT NULL,
  `client_id` int(11) DEFAULT NULL,
  `in_scope` varchar(255) DEFAULT 'NULL',
  `out_of_scope` varchar(255) DEFAULT 'NULL',
  `pre_requisite` varchar(255) DEFAULT 'NULL',
  `project_id` int(11) DEFAULT 0,
  `reason_for_choose` varchar(255) DEFAULT 'NULL',
  `vendor_completion_date` varchar(255) DEFAULT 'NULL',
  `vendor_id` int(11) DEFAULT 0,
  `vendor_project_duration` varchar(255) DEFAULT 'NULL',
  `vendor_start_date` varchar(255) DEFAULT 'NULL',
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `modified_date` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vendor_bid`
--

INSERT INTO `vendor_bid` (`bid_id`, `bid_price`, `bid_status`, `client_id`, `in_scope`, `out_of_scope`, `pre_requisite`, `project_id`, `reason_for_choose`, `vendor_completion_date`, `vendor_id`, `vendor_project_duration`, `vendor_start_date`, `created_at`, `modified_date`) VALUES
(1, '122323', 1, 1, 'test', 'test', 'NA', 1, 'NA', '2019-10-10', 1, '1', '2019-11-11', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `vendor_organisation`
--

CREATE TABLE `vendor_organisation` (
  `vendor_org_id` int(11) NOT NULL,
  `user_type` int(11) DEFAULT NULL,
  `legal_name` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `contact_person` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `expertise_category` varchar(255) DEFAULT NULL,
  `country_code` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `fax_number` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `logo_name` varchar(255) DEFAULT NULL,
  `search_terms` varchar(255) DEFAULT NULL,
  `established_date` datetime DEFAULT NULL,
  `annual_revenue` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `vendor_services_cities` varchar(255) DEFAULT NULL,
  `vendor_services` varchar(255) DEFAULT NULL,
  `products` varchar(255) DEFAULT NULL,
  `brands` varchar(255) DEFAULT NULL,
  `licenses` varchar(255) DEFAULT NULL,
  `memberships` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_date` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `vendor_organisation_id` int(11) NOT NULL,
  `contact_person_email` varchar(255) DEFAULT NULL,
  `contact_person_phone` varchar(255) DEFAULT NULL,
  `employees_count` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vendor_organisation`
--

INSERT INTO `vendor_organisation` (`vendor_org_id`, `user_type`, `legal_name`, `company_name`, `contact_person`, `address`, `city`, `province`, `postal_code`, `expertise_category`, `country_code`, `email`, `phone_number`, `fax_number`, `website`, `logo_name`, `search_terms`, `established_date`, `annual_revenue`, `description`, `vendor_services_cities`, `vendor_services`, `products`, `brands`, `licenses`, `memberships`, `created_at`, `modified_date`, `vendor_organisation_id`, `contact_person_email`, `contact_person_phone`, `employees_count`) VALUES
(1, 2, 'Vendor Organisation', 'VENDOR COMPANY', 'john', 'chennai', 'chennai', 'IN', '600042', 'PAINT', 91, 'vendor@vendor.com', '65345634534', 'fx34343', 'vendor.com', 'Vendor Logo', 'Paint', '2019-11-01 00:00:00', '100000', 'test', 'chennai', 'paint', 'paint', 'asian', 'asian', 'asian', '2019-11-15 13:52:12', '2019-11-15 13:52:12', 0, 'contact@gmail.com', '34534534', 100);

-- --------------------------------------------------------

--
-- Table structure for table `vendor_reviews_ratings`
--

CREATE TABLE `vendor_reviews_ratings` (
  `id` int(11) NOT NULL,
  `client_id` int(11) DEFAULT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `rating` float DEFAULT NULL,
  `rating_category_id` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `vendor_user`
--

CREATE TABLE `vendor_user` (
  `user_id` int(11) NOT NULL,
  `user_type` int(11) DEFAULT NULL,
  `user_role` int(11) DEFAULT NULL,
  `legal_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `account_status` int(11) DEFAULT NULL,
  `account_verification_status` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_date` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `vendor_organisation_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vendor_user`
--

INSERT INTO `vendor_user` (`user_id`, `user_type`, `user_role`, `legal_name`, `email`, `account_status`, `account_verification_status`, `created_at`, `modified_date`, `vendor_organisation_id`) VALUES
(1, 2, 1, 'Vendor User', 'user@vendor.com', 1, 1, '2019-11-15 13:08:08', '2019-11-15 13:08:08', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bidding_files`
--
ALTER TABLE `bidding_files`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bidding_products`
--
ALTER TABLE `bidding_products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bids`
--
ALTER TABLE `bids`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `client_association`
--
ALTER TABLE `client_association`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKc7tjsud5at0runmm7alt37nha` (`client_id`),
  ADD KEY `FKqlirpqk1i1ke5jj6w8l8owvgr` (`client_org_id`),
  ADD KEY `FK6dix7gjtnx5h8qc8r3ufu6pi4` (`organisation_id`);

--
-- Indexes for table `client_organisation`
--
ALTER TABLE `client_organisation`
  ADD PRIMARY KEY (`client_organisation_id`);

--
-- Indexes for table `client_organisation_amenities`
--
ALTER TABLE `client_organisation_amenities`
  ADD UNIQUE KEY `UK_mikqycdmd80dl9vklg7n5aeqc` (`amenities_id`),
  ADD KEY `FKbhnomsyyugcg4oenko8i6pqdq` (`client_organisation_client_organisation_id`);

--
-- Indexes for table `client_user`
--
ALTER TABLE `client_user`
  ADD PRIMARY KEY (`client_id`);

--
-- Indexes for table `insurance`
--
ALTER TABLE `insurance`
  ADD PRIMARY KEY (`insurance_id`);

--
-- Indexes for table `organisation_amenities_info`
--
ALTER TABLE `organisation_amenities_info`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `organisation_payment_billing_details`
--
ALTER TABLE `organisation_payment_billing_details`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `predefined_tags`
--
ALTER TABLE `predefined_tags`
  ADD PRIMARY KEY (`tag_id`);

--
-- Indexes for table `projects`
--
ALTER TABLE `projects`
  ADD PRIMARY KEY (`project_id`);

--
-- Indexes for table `project_files`
--
ALTER TABLE `project_files`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `project_products`
--
ALTER TABLE `project_products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `project_questions`
--
ALTER TABLE `project_questions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `project_reviews_ratings`
--
ALTER TABLE `project_reviews_ratings`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `project_tags`
--
ALTER TABLE `project_tags`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `rating_category`
--
ALTER TABLE `rating_category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_login`
--
ALTER TABLE `user_login`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `user_wish_list`
--
ALTER TABLE `user_wish_list`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `vendor_bid`
--
ALTER TABLE `vendor_bid`
  ADD PRIMARY KEY (`bid_id`);

--
-- Indexes for table `vendor_organisation`
--
ALTER TABLE `vendor_organisation`
  ADD PRIMARY KEY (`vendor_org_id`);

--
-- Indexes for table `vendor_reviews_ratings`
--
ALTER TABLE `vendor_reviews_ratings`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `vendor_user`
--
ALTER TABLE `vendor_user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bidding_files`
--
ALTER TABLE `bidding_files`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `bidding_products`
--
ALTER TABLE `bidding_products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `bids`
--
ALTER TABLE `bids`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `client_association`
--
ALTER TABLE `client_association`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `client_organisation`
--
ALTER TABLE `client_organisation`
  MODIFY `client_organisation_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `client_user`
--
ALTER TABLE `client_user`
  MODIFY `client_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `insurance`
--
ALTER TABLE `insurance`
  MODIFY `insurance_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `organisation_amenities_info`
--
ALTER TABLE `organisation_amenities_info`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `organisation_payment_billing_details`
--
ALTER TABLE `organisation_payment_billing_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `predefined_tags`
--
ALTER TABLE `predefined_tags`
  MODIFY `tag_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `projects`
--
ALTER TABLE `projects`
  MODIFY `project_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `project_files`
--
ALTER TABLE `project_files`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `project_products`
--
ALTER TABLE `project_products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `project_questions`
--
ALTER TABLE `project_questions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `project_reviews_ratings`
--
ALTER TABLE `project_reviews_ratings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `project_tags`
--
ALTER TABLE `project_tags`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `rating_category`
--
ALTER TABLE `rating_category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user_login`
--
ALTER TABLE `user_login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `user_wish_list`
--
ALTER TABLE `user_wish_list`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `vendor_organisation`
--
ALTER TABLE `vendor_organisation`
  MODIFY `vendor_org_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `vendor_reviews_ratings`
--
ALTER TABLE `vendor_reviews_ratings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `vendor_user`
--
ALTER TABLE `vendor_user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `client_association`
--
ALTER TABLE `client_association`
  ADD CONSTRAINT `FK6dix7gjtnx5h8qc8r3ufu6pi4` FOREIGN KEY (`organisation_id`) REFERENCES `client_organisation` (`client_organisation_id`),
  ADD CONSTRAINT `FKc7tjsud5at0runmm7alt37nha` FOREIGN KEY (`client_id`) REFERENCES `client_user` (`client_id`),
  ADD CONSTRAINT `FKqlirpqk1i1ke5jj6w8l8owvgr` FOREIGN KEY (`client_org_id`) REFERENCES `client_organisation` (`client_organisation_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
