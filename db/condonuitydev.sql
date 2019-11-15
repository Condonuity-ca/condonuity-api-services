-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 15, 2019 at 03:30 PM
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
-- Database: `concretepage`
--
CREATE DATABASE IF NOT EXISTS `concretepage` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `concretepage`;

-- --------------------------------------------------------

--
-- Table structure for table `articles`
--

CREATE TABLE `articles` (
  `articleId` int(11) NOT NULL,
  `title` varchar(120) NOT NULL,
  `category` varchar(18) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `articles`
--

INSERT INTO `articles` (`articleId`, `title`, `category`) VALUES
(1, 'hjjhfajfdhnajkfaskjfafasfasfasfdsfsdfsdfsdfsdfffffffffffff', 'iuhfiuahfiauhsfiai');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `articles`
--
ALTER TABLE `articles`
  ADD PRIMARY KEY (`articleId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `articles`
--
ALTER TABLE `articles`
  MODIFY `articleId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Database: `condonuitydev`
--
CREATE DATABASE IF NOT EXISTS `condonuitydev` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `condonuitydev`;

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
--
-- Database: `condonuity_services`
--
CREATE DATABASE IF NOT EXISTS `condonuity_services` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `condonuity_services`;

-- --------------------------------------------------------

--
-- Table structure for table `bids`
--

CREATE TABLE `bids` (
  `id` int(11) NOT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `project_id` varchar(255) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `bids_submitted` int(11) DEFAULT NULL,
  `bids_status` int(11) DEFAULT NULL,
  `in_scope` varchar(255) DEFAULT NULL,
  `out_of_scope` varchar(255) DEFAULT NULL,
  `pre_requisite` varchar(255) DEFAULT NULL,
  `reason_for_choose` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `brands`
--

CREATE TABLE `brands` (
  `id` int(11) NOT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `client_id` int(11) NOT NULL,
  `user_type` int(11) DEFAULT NULL,
  `client_name` varchar(255) DEFAULT NULL,
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
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`client_id`, `user_type`, `client_name`, `corporate_number`, `registration_date`, `address`, `city`, `province`, `postal_code`, `country_code`, `phone_number`, `fax_number`, `units`, `voting_units`, `created_at`) VALUES
(1, 1, 'CLDS', '12345clds', '2019-10-01', 'chennai', 'tamilnadu', 'india', '600042', 1, '123123123', '123123', 100, 50, '2019-10-08 12:15:26');

-- --------------------------------------------------------

--
-- Table structure for table `client_amenities_info`
--

CREATE TABLE `client_amenities_info` (
  `id` int(11) NOT NULL,
  `client_id` int(11) DEFAULT NULL,
  `gym` int(11) DEFAULT NULL,
  `party_room` int(11) DEFAULT NULL,
  `elevators_count` int(11) DEFAULT NULL,
  `swim_pool` int(11) DEFAULT NULL,
  `pool_type` varchar(255) DEFAULT NULL,
  `parking` int(11) DEFAULT NULL,
  `parking_type` varchar(255) DEFAULT NULL,
  `parting_spots_count` int(11) DEFAULT NULL,
  `other_information` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `client_billing_address`
--

CREATE TABLE `client_billing_address` (
  `id` int(11) NOT NULL,
  `client_id` int(11) DEFAULT NULL,
  `street_address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `client_other_info`
--

CREATE TABLE `client_other_info` (
  `id` int(11) NOT NULL,
  `client_id` int(11) DEFAULT NULL,
  `management_company` varchar(255) DEFAULT NULL,
  `registration_date` date DEFAULT NULL,
  `units` int(11) DEFAULT NULL,
  `voting_units` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `client_payment_card_details`
--

CREATE TABLE `client_payment_card_details` (
  `id` int(11) NOT NULL,
  `client_id` int(11) DEFAULT NULL,
  `card_number` varchar(255) DEFAULT NULL,
  `name_on_card` varchar(255) DEFAULT NULL,
  `expiry_date` varchar(255) DEFAULT NULL,
  `security_code` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `client_project`
--

CREATE TABLE `client_project` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `project_name` varchar(255) DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  `bid_end_date` datetime DEFAULT NULL,
  `project_start_date` datetime DEFAULT NULL,
  `project_completion_deadline` datetime DEFAULT NULL,
  `estimated_budget` double DEFAULT NULL,
  `duration` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `rating` float DEFAULT NULL,
  `contract_type` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `client_repository`
--

CREATE TABLE `client_repository` (
  `id` int(11) NOT NULL,
  `created_client_id` int(11) DEFAULT NULL,
  `leasee_client_id` int(11) DEFAULT NULL,
  `owner_name` varchar(255) DEFAULT NULL,
  `lease_info` varchar(255) DEFAULT NULL,
  `lien_info` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `repository_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `client_tasks`
--

CREATE TABLE `client_tasks` (
  `id` int(11) NOT NULL,
  `task_name` varchar(255) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `created_client_id` int(11) DEFAULT NULL,
  `assignee_client_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `open_date` datetime DEFAULT NULL,
  `closed_date` datetime DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `client_task_files`
--

CREATE TABLE `client_task_files` (
  `id` int(11) NOT NULL,
  `task_id` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `client_user`
--

CREATE TABLE `client_user` (
  `user_id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT 'NULL',
  `client_id` int(11) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `corporate_account_id` varchar(255) DEFAULT NULL,
  `user_role` int(11) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `legal_name` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `country_code` int(11) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `phone` mediumtext DEFAULT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client_user`
--

INSERT INTO `client_user` (`user_id`, `email`, `client_id`, `user_type`, `corporate_account_id`, `user_role`, `first_name`, `last_name`, `legal_name`, `created_at`, `country_code`, `city`, `phone`, `status`) VALUES
(1, 'prakash@clds.com', 1, 1, 'corp12345', 1, 'prakash', 'prakash', 'prakashExplorer', '2019-10-08 13:44:57', 1, 'Chennai', '123123123', 20),
(2, 'user@clds.com', 1, 1, 'corp12345', 1, 'user', 'test', 'client user', '2019-10-08 13:42:31', 1, 'Chennai', '123123123', 20);

-- --------------------------------------------------------

--
-- Table structure for table `company_documents_verification`
--

CREATE TABLE `company_documents_verification` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `document_type` varchar(255) DEFAULT NULL,
  `document_name` varchar(255) DEFAULT NULL,
  `vertification_status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `contract_types`
--

CREATE TABLE `contract_types` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `corporate_account`
--

CREATE TABLE `corporate_account` (
  `corporate_account_id` int(11) NOT NULL,
  `client_id` int(11) DEFAULT NULL,
  `client_role` int(11) DEFAULT NULL,
  `corporation_name` varchar(255) DEFAULT NULL,
  `corporate_number` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `fax_number` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `country`
--

CREATE TABLE `country` (
  `id` int(11) NOT NULL,
  `code` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `continent_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `country`
--

INSERT INTO `country` (`id`, `code`, `name`, `continent_name`) VALUES
(1, 1, 'IN', 'India');

-- --------------------------------------------------------

--
-- Table structure for table `group_settings`
--

CREATE TABLE `group_settings` (
  `id` int(11) NOT NULL,
  `user_type` int(11) DEFAULT NULL,
  `max_count` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `insurance`
--

CREATE TABLE `insurance` (
  `id` int(11) NOT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `provider` varchar(255) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `licenses`
--

CREATE TABLE `licenses` (
  `id` int(11) NOT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `license_number` varchar(255) DEFAULT NULL,
  `expiry_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `memberships`
--

CREATE TABLE `memberships` (
  `id` int(11) NOT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `membership_provider` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `message_board`
--

CREATE TABLE `message_board` (
  `id` int(11) NOT NULL,
  `type` int(11) DEFAULT NULL,
  `topic_name` varchar(255) DEFAULT NULL,
  `topic_comment` varchar(255) DEFAULT NULL,
  `attachments` varchar(255) DEFAULT NULL,
  `created_user_id` int(11) DEFAULT NULL,
  `created_user_type` int(11) DEFAULT NULL,
  `target_user_id` int(11) DEFAULT NULL,
  `target_user_type` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `message_board_comments`
--

CREATE TABLE `message_board_comments` (
  `id` int(11) NOT NULL,
  `message_board_id` int(11) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `attachments` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `message_board_type`
--

CREATE TABLE `message_board_type` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `notification_settings`
--

CREATE TABLE `notification_settings` (
  `id` int(11) NOT NULL,
  `notification_id` int(11) DEFAULT NULL,
  `notification_module` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `project_files`
--

CREATE TABLE `project_files` (
  `id` int(11) NOT NULL,
  `project_id` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `project_products`
--

CREATE TABLE `project_products` (
  `id` int(11) NOT NULL,
  `project_id` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `project_questions`
--

CREATE TABLE `project_questions` (
  `id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `question` varchar(255) DEFAULT NULL,
  `answer` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE `reviews` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `review` float DEFAULT NULL,
  `review_module` int(11) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `review_modules`
--

CREATE TABLE `review_modules` (
  `id` int(11) NOT NULL,
  `review_module` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `serviced_areas`
--

CREATE TABLE `serviced_areas` (
  `id` int(11) NOT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  `area_name` varchar(255) DEFAULT NULL,
  `area_code` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `services`
--

CREATE TABLE `services` (
  `id` int(11) NOT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

CREATE TABLE `status` (
  `id` int(11) NOT NULL,
  `status_id` int(11) NOT NULL,
  `status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `subscription`
--

CREATE TABLE `subscription` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `subscription_name` varchar(255) DEFAULT NULL,
  `subscription_type` varchar(255) DEFAULT NULL,
  `tag_status` int(11) DEFAULT NULL,
  `expiry_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `subscription_types`
--

CREATE TABLE `subscription_types` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `support_user`
--

CREATE TABLE `support_user` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `country_code` int(11) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` mediumtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tags`
--

CREATE TABLE `tags` (
  `id` int(11) NOT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  `tag_name` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `tag_status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_account`
--

CREATE TABLE `user_account` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `username` varchar(120) NOT NULL,
  `password` varchar(256) NOT NULL,
  `user_type` int(11) NOT NULL,
  `user_role` int(11) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_account`
--

INSERT INTO `user_account` (`id`, `user_id`, `username`, `password`, `user_type`, `user_role`, `created_date`) VALUES
(1, 1, 'prakash@clds.com', '0hmCKKFlbtY5BKDvdCZjBg==', 1, 1, '2019-09-26 12:27:55'),
(2, 2, 'user@clds.com', '0hmCKKFlbtY5BKDvdCZjBg==', 1, 1, '2019-09-26 12:27:55');

-- --------------------------------------------------------

--
-- Table structure for table `user_register_verification`
--

CREATE TABLE `user_register_verification` (
  `id` int(11) NOT NULL,
  `user_type` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `vertification_status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE `user_roles` (
  `id` int(11) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `privilege` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`id`, `role_id`, `role`, `privilege`) VALUES
(1, 1, 'ADMIN', 'RW'),
(2, 2, 'MANAGER', 'R'),
(3, 3, 'ASSISTANT_MANAGER', 'R'),
(4, 4, 'BOARD_MEMBER', 'R'),
(5, 5, 'SUPPORT_USER', 'RW');

-- --------------------------------------------------------

--
-- Table structure for table `user_type`
--

CREATE TABLE `user_type` (
  `id` int(11) NOT NULL,
  `user_type_id` int(11) DEFAULT NULL,
  `user_type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_wish_list`
--

CREATE TABLE `user_wish_list` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `wish_user_id` int(11) DEFAULT NULL,
  `wish_user_type` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `vendor`
--

CREATE TABLE `vendor` (
  `vendor_id` int(11) NOT NULL,
  `user_type` int(11) DEFAULT NULL,
  `legal_name` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `contact_person` varchar(255) DEFAULT NULL,
  `rating` float DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `expertise_category` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `country_code` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `fax_number` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `logo_name` varchar(255) DEFAULT NULL,
  `search_terms` varchar(255) DEFAULT NULL,
  `established_date` datetime DEFAULT NULL,
  `no_of_employee` int(11) DEFAULT NULL,
  `annual_revenue` double DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `vendor_products`
--

CREATE TABLE `vendor_products` (
  `id` int(11) NOT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `available_products` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `vendor_user`
--

CREATE TABLE `vendor_user` (
  `user_id` int(11) NOT NULL,
  `user_type` int(11) DEFAULT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `legal_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `warrenty`
--

CREATE TABLE `warrenty` (
  `id` int(11) NOT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `penalties` varchar(255) DEFAULT NULL,
  `warrenty_period` varchar(255) DEFAULT NULL,
  `total_price` float DEFAULT NULL,
  `warrenty_number` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bids`
--
ALTER TABLE `bids`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `brands`
--
ALTER TABLE `brands`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`client_id`);

--
-- Indexes for table `client_amenities_info`
--
ALTER TABLE `client_amenities_info`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `client_billing_address`
--
ALTER TABLE `client_billing_address`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `client_other_info`
--
ALTER TABLE `client_other_info`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `client_payment_card_details`
--
ALTER TABLE `client_payment_card_details`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `client_project`
--
ALTER TABLE `client_project`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `client_repository`
--
ALTER TABLE `client_repository`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `client_tasks`
--
ALTER TABLE `client_tasks`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `client_task_files`
--
ALTER TABLE `client_task_files`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `client_user`
--
ALTER TABLE `client_user`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `company_documents_verification`
--
ALTER TABLE `company_documents_verification`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `contract_types`
--
ALTER TABLE `contract_types`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `corporate_account`
--
ALTER TABLE `corporate_account`
  ADD PRIMARY KEY (`corporate_account_id`);

--
-- Indexes for table `country`
--
ALTER TABLE `country`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `group_settings`
--
ALTER TABLE `group_settings`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `insurance`
--
ALTER TABLE `insurance`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `licenses`
--
ALTER TABLE `licenses`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `memberships`
--
ALTER TABLE `memberships`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `message_board`
--
ALTER TABLE `message_board`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `message_board_comments`
--
ALTER TABLE `message_board_comments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `message_board_type`
--
ALTER TABLE `message_board_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `notification_settings`
--
ALTER TABLE `notification_settings`
  ADD PRIMARY KEY (`id`);

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
-- Indexes for table `reviews`
--
ALTER TABLE `reviews`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `review_modules`
--
ALTER TABLE `review_modules`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `serviced_areas`
--
ALTER TABLE `serviced_areas`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `services`
--
ALTER TABLE `services`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `subscription`
--
ALTER TABLE `subscription`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `subscription_types`
--
ALTER TABLE `subscription_types`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `support_user`
--
ALTER TABLE `support_user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tags`
--
ALTER TABLE `tags`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_account`
--
ALTER TABLE `user_account`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_register_verification`
--
ALTER TABLE `user_register_verification`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_type`
--
ALTER TABLE `user_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_wish_list`
--
ALTER TABLE `user_wish_list`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `vendor`
--
ALTER TABLE `vendor`
  ADD PRIMARY KEY (`vendor_id`);

--
-- Indexes for table `vendor_products`
--
ALTER TABLE `vendor_products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `vendor_user`
--
ALTER TABLE `vendor_user`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `warrenty`
--
ALTER TABLE `warrenty`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bids`
--
ALTER TABLE `bids`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `brands`
--
ALTER TABLE `brands`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `client_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `client_amenities_info`
--
ALTER TABLE `client_amenities_info`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `client_billing_address`
--
ALTER TABLE `client_billing_address`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `client_other_info`
--
ALTER TABLE `client_other_info`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `client_payment_card_details`
--
ALTER TABLE `client_payment_card_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `client_project`
--
ALTER TABLE `client_project`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `client_repository`
--
ALTER TABLE `client_repository`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `client_tasks`
--
ALTER TABLE `client_tasks`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `client_task_files`
--
ALTER TABLE `client_task_files`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `client_user`
--
ALTER TABLE `client_user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `company_documents_verification`
--
ALTER TABLE `company_documents_verification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `contract_types`
--
ALTER TABLE `contract_types`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `corporate_account`
--
ALTER TABLE `corporate_account`
  MODIFY `corporate_account_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `country`
--
ALTER TABLE `country`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `group_settings`
--
ALTER TABLE `group_settings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `insurance`
--
ALTER TABLE `insurance`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `licenses`
--
ALTER TABLE `licenses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `memberships`
--
ALTER TABLE `memberships`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `message_board`
--
ALTER TABLE `message_board`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `message_board_comments`
--
ALTER TABLE `message_board_comments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `message_board_type`
--
ALTER TABLE `message_board_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `notification_settings`
--
ALTER TABLE `notification_settings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `reviews`
--
ALTER TABLE `reviews`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `review_modules`
--
ALTER TABLE `review_modules`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `serviced_areas`
--
ALTER TABLE `serviced_areas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `services`
--
ALTER TABLE `services`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `status`
--
ALTER TABLE `status`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `subscription`
--
ALTER TABLE `subscription`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `subscription_types`
--
ALTER TABLE `subscription_types`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `support_user`
--
ALTER TABLE `support_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tags`
--
ALTER TABLE `tags`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user_account`
--
ALTER TABLE `user_account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `user_register_verification`
--
ALTER TABLE `user_register_verification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user_roles`
--
ALTER TABLE `user_roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `user_type`
--
ALTER TABLE `user_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user_wish_list`
--
ALTER TABLE `user_wish_list`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `vendor`
--
ALTER TABLE `vendor`
  MODIFY `vendor_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `vendor_products`
--
ALTER TABLE `vendor_products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `vendor_user`
--
ALTER TABLE `vendor_user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `warrenty`
--
ALTER TABLE `warrenty`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Database: `contonuity`
--
CREATE DATABASE IF NOT EXISTS `contonuity` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `contonuity`;

-- --------------------------------------------------------

--
-- Table structure for table `bids`
--

CREATE TABLE `bids` (
  `id` int(11) NOT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `project_id` varchar(255) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `bids_submitted` int(11) DEFAULT NULL,
  `bids_status` int(11) DEFAULT NULL,
  `in_scope` varchar(255) DEFAULT NULL,
  `out_of_scope` varchar(255) DEFAULT NULL,
  `pre_requisite` varchar(255) DEFAULT NULL,
  `reason_for_choose` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `brands`
--

CREATE TABLE `brands` (
  `id` int(11) NOT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `account_id` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `user_type` int(11) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `client_name` varchar(255) DEFAULT NULL,
  `corporate_number` varchar(255) DEFAULT NULL,
  `company_management_name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `number_of_units` int(11) DEFAULT NULL,
  `registration_date` date DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `country_code` int(11) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` mediumtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `client_project`
--

CREATE TABLE `client_project` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `project_name` varchar(255) DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  `bid_end_date` datetime DEFAULT NULL,
  `project_start_date` datetime DEFAULT NULL,
  `project_completion_deadline` datetime DEFAULT NULL,
  `estimated_budget` double DEFAULT NULL,
  `duration` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `rating` float DEFAULT NULL,
  `contract_type` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `client_repository`
--

CREATE TABLE `client_repository` (
  `id` int(11) NOT NULL,
  `created_client_id` int(11) DEFAULT NULL,
  `leasee_client_id` int(11) DEFAULT NULL,
  `owner_name` varchar(255) DEFAULT NULL,
  `lease_info` varchar(255) DEFAULT NULL,
  `lien_info` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `repository_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `client_tasks`
--

CREATE TABLE `client_tasks` (
  `id` int(11) NOT NULL,
  `task_name` varchar(255) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `created_client_id` int(11) DEFAULT NULL,
  `assignee_client_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `open_date` datetime DEFAULT NULL,
  `closed_date` datetime DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `client_task_files`
--

CREATE TABLE `client_task_files` (
  `id` int(11) NOT NULL,
  `task_id` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `client_user`
--

CREATE TABLE `client_user` (
  `account_id` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `user_type` int(11) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `legal_name` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `country_code` int(11) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` mediumtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `company_documents_verification`
--

CREATE TABLE `company_documents_verification` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `document_type` varchar(255) DEFAULT NULL,
  `document_name` varchar(255) DEFAULT NULL,
  `vertification_status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `contract_types`
--

CREATE TABLE `contract_types` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `corporate_account`
--

CREATE TABLE `corporate_account` (
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `country`
--

CREATE TABLE `country` (
  `id` int(11) NOT NULL,
  `code` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `continent_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `established_dates`
--

CREATE TABLE `established_dates` (
  `id` int(11) NOT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `established_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `group_settings`
--

CREATE TABLE `group_settings` (
  `id` int(11) NOT NULL,
  `user_type` int(11) DEFAULT NULL,
  `max_count` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `insurance`
--

CREATE TABLE `insurance` (
  `id` int(11) NOT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `provider` varchar(255) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `licenses`
--

CREATE TABLE `licenses` (
  `id` int(11) NOT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `license_number` varchar(255) DEFAULT NULL,
  `expiry_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `memberships`
--

CREATE TABLE `memberships` (
  `id` int(11) NOT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `membership_provider` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `message_board`
--

CREATE TABLE `message_board` (
  `id` int(11) NOT NULL,
  `type` int(11) DEFAULT NULL,
  `topic_name` varchar(255) DEFAULT NULL,
  `topic_comment` varchar(255) DEFAULT NULL,
  `attachments` varchar(255) DEFAULT NULL,
  `created_user_id` int(11) DEFAULT NULL,
  `created_user_type` int(11) DEFAULT NULL,
  `target_user_id` int(11) DEFAULT NULL,
  `target_user_type` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `message_board_comments`
--

CREATE TABLE `message_board_comments` (
  `id` int(11) NOT NULL,
  `message_board_id` int(11) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `attachments` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `message_board_type`
--

CREATE TABLE `message_board_type` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `notification_settings`
--

CREATE TABLE `notification_settings` (
  `id` int(11) NOT NULL,
  `notification_id` int(11) DEFAULT NULL,
  `notification_module` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `project_files`
--

CREATE TABLE `project_files` (
  `id` int(11) NOT NULL,
  `project_id` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `project_products`
--

CREATE TABLE `project_products` (
  `id` int(11) NOT NULL,
  `project_id` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `project_questions`
--

CREATE TABLE `project_questions` (
  `id` int(11) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `question` varchar(255) DEFAULT NULL,
  `answer` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE `reviews` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `review` float DEFAULT NULL,
  `review_module` int(11) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `review_modules`
--

CREATE TABLE `review_modules` (
  `id` int(11) NOT NULL,
  `review_module` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `serviced_areas`
--

CREATE TABLE `serviced_areas` (
  `id` int(11) NOT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  `area_name` varchar(255) DEFAULT NULL,
  `area_code` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `services`
--

CREATE TABLE `services` (
  `id` int(11) NOT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

CREATE TABLE `status` (
  `id` int(11) NOT NULL,
  `status_id` int(11) NOT NULL,
  `status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `subscription`
--

CREATE TABLE `subscription` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `subscription_name` varchar(255) DEFAULT NULL,
  `subscription_type` varchar(255) DEFAULT NULL,
  `tag_status` int(11) DEFAULT NULL,
  `expiry_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `subscription_types`
--

CREATE TABLE `subscription_types` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `support_user`
--

CREATE TABLE `support_user` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `country_code` int(11) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` mediumtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tags`
--

CREATE TABLE `tags` (
  `id` int(11) NOT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  `tag_name` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `tag_status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `useraccount`
--

CREATE TABLE `useraccount` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `user_role` int(11) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_register_verification`
--

CREATE TABLE `user_register_verification` (
  `id` int(11) NOT NULL,
  `user_type` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `vertification_status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE `user_roles` (
  `id` int(11) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `privilege` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_type`
--

CREATE TABLE `user_type` (
  `id` int(11) NOT NULL,
  `user_type_id` int(11) DEFAULT NULL,
  `user_type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_wish_list`
--

CREATE TABLE `user_wish_list` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `wish_user_id` int(11) DEFAULT NULL,
  `wish_user_type` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `vendor`
--

CREATE TABLE `vendor` (
  `account_id` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `user_type` int(11) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `legal_name` varchar(255) DEFAULT NULL,
  `rating` float DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `expertise_category` varchar(255) DEFAULT NULL,
  `no_of_employee` int(11) DEFAULT NULL,
  `annual_revenue` double DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `country_code` int(11) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` mediumtext DEFAULT NULL,
  `logo_name` varchar(255) DEFAULT NULL,
  `search_terms` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `vendor_products`
--

CREATE TABLE `vendor_products` (
  `id` int(11) NOT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `available_products` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `warrenty`
--

CREATE TABLE `warrenty` (
  `id` int(11) NOT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `penalties` varchar(255) DEFAULT NULL,
  `warrenty_period` varchar(255) DEFAULT NULL,
  `total_price` float DEFAULT NULL,
  `warrenty_number` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bids`
--
ALTER TABLE `bids`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `brands`
--
ALTER TABLE `brands`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `client_project`
--
ALTER TABLE `client_project`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `client_repository`
--
ALTER TABLE `client_repository`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `client_tasks`
--
ALTER TABLE `client_tasks`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `client_task_files`
--
ALTER TABLE `client_task_files`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `client_user`
--
ALTER TABLE `client_user`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `company_documents_verification`
--
ALTER TABLE `company_documents_verification`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `contract_types`
--
ALTER TABLE `contract_types`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `corporate_account`
--
ALTER TABLE `corporate_account`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `country`
--
ALTER TABLE `country`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `established_dates`
--
ALTER TABLE `established_dates`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `group_settings`
--
ALTER TABLE `group_settings`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `insurance`
--
ALTER TABLE `insurance`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `licenses`
--
ALTER TABLE `licenses`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `memberships`
--
ALTER TABLE `memberships`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `message_board`
--
ALTER TABLE `message_board`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `message_board_comments`
--
ALTER TABLE `message_board_comments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `message_board_type`
--
ALTER TABLE `message_board_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `notification_settings`
--
ALTER TABLE `notification_settings`
  ADD PRIMARY KEY (`id`),
  ADD KEY `notification_id` (`notification_id`);

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
-- Indexes for table `reviews`
--
ALTER TABLE `reviews`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `review_modules`
--
ALTER TABLE `review_modules`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `serviced_areas`
--
ALTER TABLE `serviced_areas`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `services`
--
ALTER TABLE `services`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `subscription`
--
ALTER TABLE `subscription`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `subscription_types`
--
ALTER TABLE `subscription_types`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `support_user`
--
ALTER TABLE `support_user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tags`
--
ALTER TABLE `tags`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `useraccount`
--
ALTER TABLE `useraccount`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_register_verification`
--
ALTER TABLE `user_register_verification`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_type`
--
ALTER TABLE `user_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_wish_list`
--
ALTER TABLE `user_wish_list`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `vendor`
--
ALTER TABLE `vendor`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `vendor_products`
--
ALTER TABLE `vendor_products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `warrenty`
--
ALTER TABLE `warrenty`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bids`
--
ALTER TABLE `bids`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `brands`
--
ALTER TABLE `brands`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `client_project`
--
ALTER TABLE `client_project`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `client_repository`
--
ALTER TABLE `client_repository`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `client_tasks`
--
ALTER TABLE `client_tasks`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `client_task_files`
--
ALTER TABLE `client_task_files`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `client_user`
--
ALTER TABLE `client_user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `company_documents_verification`
--
ALTER TABLE `company_documents_verification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `contract_types`
--
ALTER TABLE `contract_types`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `corporate_account`
--
ALTER TABLE `corporate_account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `country`
--
ALTER TABLE `country`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `established_dates`
--
ALTER TABLE `established_dates`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `group_settings`
--
ALTER TABLE `group_settings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `insurance`
--
ALTER TABLE `insurance`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `licenses`
--
ALTER TABLE `licenses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `memberships`
--
ALTER TABLE `memberships`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `message_board`
--
ALTER TABLE `message_board`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `message_board_comments`
--
ALTER TABLE `message_board_comments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `message_board_type`
--
ALTER TABLE `message_board_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `notification_settings`
--
ALTER TABLE `notification_settings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `reviews`
--
ALTER TABLE `reviews`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `review_modules`
--
ALTER TABLE `review_modules`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `serviced_areas`
--
ALTER TABLE `serviced_areas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `services`
--
ALTER TABLE `services`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `status`
--
ALTER TABLE `status`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `subscription`
--
ALTER TABLE `subscription`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `subscription_types`
--
ALTER TABLE `subscription_types`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `support_user`
--
ALTER TABLE `support_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tags`
--
ALTER TABLE `tags`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `useraccount`
--
ALTER TABLE `useraccount`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user_register_verification`
--
ALTER TABLE `user_register_verification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user_roles`
--
ALTER TABLE `user_roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user_type`
--
ALTER TABLE `user_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user_wish_list`
--
ALTER TABLE `user_wish_list`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `vendor`
--
ALTER TABLE `vendor`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `vendor_products`
--
ALTER TABLE `vendor_products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `warrenty`
--
ALTER TABLE `warrenty`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `notification_settings`
--
ALTER TABLE `notification_settings`
  ADD CONSTRAINT `notification_settings_ibfk_1` FOREIGN KEY (`notification_id`) REFERENCES `client` (`user_id`),
  ADD CONSTRAINT `notification_settings_ibfk_2` FOREIGN KEY (`notification_id`) REFERENCES `vendor` (`user_id`);
--
-- Database: `phpmyadmin`
--
CREATE DATABASE IF NOT EXISTS `phpmyadmin` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `phpmyadmin`;

-- --------------------------------------------------------

--
-- Table structure for table `pma__bookmark`
--

CREATE TABLE `pma__bookmark` (
  `id` int(10) UNSIGNED NOT NULL,
  `dbase` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `user` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `label` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `query` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Bookmarks';

-- --------------------------------------------------------

--
-- Table structure for table `pma__central_columns`
--

CREATE TABLE `pma__central_columns` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `col_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `col_type` varchar(64) COLLATE utf8_bin NOT NULL,
  `col_length` text COLLATE utf8_bin DEFAULT NULL,
  `col_collation` varchar(64) COLLATE utf8_bin NOT NULL,
  `col_isNull` tinyint(1) NOT NULL,
  `col_extra` varchar(255) COLLATE utf8_bin DEFAULT '',
  `col_default` text COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Central list of columns';

-- --------------------------------------------------------

--
-- Table structure for table `pma__column_info`
--

CREATE TABLE `pma__column_info` (
  `id` int(5) UNSIGNED NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `column_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `comment` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `mimetype` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `transformation` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `transformation_options` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `input_transformation` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `input_transformation_options` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Column information for phpMyAdmin';

-- --------------------------------------------------------

--
-- Table structure for table `pma__designer_settings`
--

CREATE TABLE `pma__designer_settings` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `settings_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Settings related to Designer';

--
-- Dumping data for table `pma__designer_settings`
--

INSERT INTO `pma__designer_settings` (`username`, `settings_data`) VALUES
('root', '{\"snap_to_grid\":\"off\",\"relation_lines\":\"true\",\"angular_direct\":\"direct\"}');

-- --------------------------------------------------------

--
-- Table structure for table `pma__export_templates`
--

CREATE TABLE `pma__export_templates` (
  `id` int(5) UNSIGNED NOT NULL,
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `export_type` varchar(10) COLLATE utf8_bin NOT NULL,
  `template_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `template_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Saved export templates';

-- --------------------------------------------------------

--
-- Table structure for table `pma__favorite`
--

CREATE TABLE `pma__favorite` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `tables` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Favorite tables';

-- --------------------------------------------------------

--
-- Table structure for table `pma__history`
--

CREATE TABLE `pma__history` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `username` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `timevalue` timestamp NOT NULL DEFAULT current_timestamp(),
  `sqlquery` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='SQL history for phpMyAdmin';

-- --------------------------------------------------------

--
-- Table structure for table `pma__navigationhiding`
--

CREATE TABLE `pma__navigationhiding` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `item_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `item_type` varchar(64) COLLATE utf8_bin NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Hidden items of navigation tree';

--
-- Dumping data for table `pma__navigationhiding`
--

INSERT INTO `pma__navigationhiding` (`username`, `item_name`, `item_type`, `db_name`, `table_name`) VALUES
('root', 'client', 'table', 'security_services_condonuity', '');

-- --------------------------------------------------------

--
-- Table structure for table `pma__pdf_pages`
--

CREATE TABLE `pma__pdf_pages` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `page_nr` int(10) UNSIGNED NOT NULL,
  `page_descr` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='PDF relation pages for phpMyAdmin';

-- --------------------------------------------------------

--
-- Table structure for table `pma__recent`
--

CREATE TABLE `pma__recent` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `tables` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Recently accessed tables';

--
-- Dumping data for table `pma__recent`
--

INSERT INTO `pma__recent` (`username`, `tables`) VALUES
('root', '[{\"db\":\"condonuitydev\",\"table\":\"vendor_user\"},{\"db\":\"condonuitydev\",\"table\":\"user_login\"},{\"db\":\"condonuitydev\",\"table\":\"vendor_organisation\"},{\"db\":\"condonuitydev\",\"table\":\"project_questions\"},{\"db\":\"condonuitydev\",\"table\":\"vendor_bid\"},{\"db\":\"condonuitydev\",\"table\":\"projects\"},{\"db\":\"condonuitydev\",\"table\":\"client_organisation\"},{\"db\":\"condonuitydev\",\"table\":\"client_user\"},{\"db\":\"condonuitydev\",\"table\":\"client_association\"},{\"db\":\"condonuitydev\",\"table\":\"organisation_payment_billing_details\"}]');

-- --------------------------------------------------------

--
-- Table structure for table `pma__relation`
--

CREATE TABLE `pma__relation` (
  `master_db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `master_table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `master_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Relation table';

-- --------------------------------------------------------

--
-- Table structure for table `pma__savedsearches`
--

CREATE TABLE `pma__savedsearches` (
  `id` int(5) UNSIGNED NOT NULL,
  `username` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `search_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `search_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Saved searches';

-- --------------------------------------------------------

--
-- Table structure for table `pma__table_coords`
--

CREATE TABLE `pma__table_coords` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `pdf_page_number` int(11) NOT NULL DEFAULT 0,
  `x` float UNSIGNED NOT NULL DEFAULT 0,
  `y` float UNSIGNED NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table coordinates for phpMyAdmin PDF output';

-- --------------------------------------------------------

--
-- Table structure for table `pma__table_info`
--

CREATE TABLE `pma__table_info` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `display_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table information for phpMyAdmin';

-- --------------------------------------------------------

--
-- Table structure for table `pma__table_uiprefs`
--

CREATE TABLE `pma__table_uiprefs` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `prefs` text COLLATE utf8_bin NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Tables'' UI preferences';

--
-- Dumping data for table `pma__table_uiprefs`
--

INSERT INTO `pma__table_uiprefs` (`username`, `db_name`, `table_name`, `prefs`, `last_update`) VALUES
('root', 'condonuity_services', 'user_account', '{\"CREATE_TIME\":\"2019-10-08 17:58:51\",\"col_order\":[0,1,2,3,4,5,6],\"col_visib\":[1,1,1,1,1,1,1]}', '2019-10-08 12:30:22'),
('root', 'security_services_condonuity', 'client_association', '{\"sorted_col\":\"`client_id` ASC\"}', '2019-10-25 07:47:36'),
('root', 'security_services_condonuity', 'organisation', '{\"CREATE_TIME\":\"2019-10-15 16:25:43\"}', '2019-10-22 07:03:02'),
('root', 'security_services_condonuity', 'vendor_user', '{\"sorted_col\":\"`user_id` ASC\"}', '2019-11-01 12:00:27'),
('root', 'spring_security_sample', 'user_cookie', '{\"sorted_col\":\"`user_cookie`.`id` ASC\"}', '2019-09-27 12:36:39'),
('root', 'springbootdb', 'user', '{\"sorted_col\":\"`user`.`id`  ASC\"}', '2019-09-20 10:28:45');

-- --------------------------------------------------------

--
-- Table structure for table `pma__tracking`
--

CREATE TABLE `pma__tracking` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `version` int(10) UNSIGNED NOT NULL,
  `date_created` datetime NOT NULL,
  `date_updated` datetime NOT NULL,
  `schema_snapshot` text COLLATE utf8_bin NOT NULL,
  `schema_sql` text COLLATE utf8_bin DEFAULT NULL,
  `data_sql` longtext COLLATE utf8_bin DEFAULT NULL,
  `tracking` set('UPDATE','REPLACE','INSERT','DELETE','TRUNCATE','CREATE DATABASE','ALTER DATABASE','DROP DATABASE','CREATE TABLE','ALTER TABLE','RENAME TABLE','DROP TABLE','CREATE INDEX','DROP INDEX','CREATE VIEW','ALTER VIEW','DROP VIEW') COLLATE utf8_bin DEFAULT NULL,
  `tracking_active` int(1) UNSIGNED NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Database changes tracking for phpMyAdmin';

-- --------------------------------------------------------

--
-- Table structure for table `pma__userconfig`
--

CREATE TABLE `pma__userconfig` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `timevalue` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `config_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User preferences storage for phpMyAdmin';

--
-- Dumping data for table `pma__userconfig`
--

INSERT INTO `pma__userconfig` (`username`, `timevalue`, `config_data`) VALUES
('', '2019-10-19 10:28:35', '{\"Console\\/Mode\":\"collapse\"}'),
('root', '2019-10-19 08:13:55', '{\"Console\\/Mode\":\"collapse\",\"NavigationWidth\":286}');

-- --------------------------------------------------------

--
-- Table structure for table `pma__usergroups`
--

CREATE TABLE `pma__usergroups` (
  `usergroup` varchar(64) COLLATE utf8_bin NOT NULL,
  `tab` varchar(64) COLLATE utf8_bin NOT NULL,
  `allowed` enum('Y','N') COLLATE utf8_bin NOT NULL DEFAULT 'N'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User groups with configured menu items';

-- --------------------------------------------------------

--
-- Table structure for table `pma__users`
--

CREATE TABLE `pma__users` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `usergroup` varchar(64) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Users and their assignments to user groups';

--
-- Indexes for dumped tables
--

--
-- Indexes for table `pma__bookmark`
--
ALTER TABLE `pma__bookmark`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pma__central_columns`
--
ALTER TABLE `pma__central_columns`
  ADD PRIMARY KEY (`db_name`,`col_name`);

--
-- Indexes for table `pma__column_info`
--
ALTER TABLE `pma__column_info`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `db_name` (`db_name`,`table_name`,`column_name`);

--
-- Indexes for table `pma__designer_settings`
--
ALTER TABLE `pma__designer_settings`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `pma__export_templates`
--
ALTER TABLE `pma__export_templates`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `u_user_type_template` (`username`,`export_type`,`template_name`);

--
-- Indexes for table `pma__favorite`
--
ALTER TABLE `pma__favorite`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `pma__history`
--
ALTER TABLE `pma__history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `username` (`username`,`db`,`table`,`timevalue`);

--
-- Indexes for table `pma__navigationhiding`
--
ALTER TABLE `pma__navigationhiding`
  ADD PRIMARY KEY (`username`,`item_name`,`item_type`,`db_name`,`table_name`);

--
-- Indexes for table `pma__pdf_pages`
--
ALTER TABLE `pma__pdf_pages`
  ADD PRIMARY KEY (`page_nr`),
  ADD KEY `db_name` (`db_name`);

--
-- Indexes for table `pma__recent`
--
ALTER TABLE `pma__recent`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `pma__relation`
--
ALTER TABLE `pma__relation`
  ADD PRIMARY KEY (`master_db`,`master_table`,`master_field`),
  ADD KEY `foreign_field` (`foreign_db`,`foreign_table`);

--
-- Indexes for table `pma__savedsearches`
--
ALTER TABLE `pma__savedsearches`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `u_savedsearches_username_dbname` (`username`,`db_name`,`search_name`);

--
-- Indexes for table `pma__table_coords`
--
ALTER TABLE `pma__table_coords`
  ADD PRIMARY KEY (`db_name`,`table_name`,`pdf_page_number`);

--
-- Indexes for table `pma__table_info`
--
ALTER TABLE `pma__table_info`
  ADD PRIMARY KEY (`db_name`,`table_name`);

--
-- Indexes for table `pma__table_uiprefs`
--
ALTER TABLE `pma__table_uiprefs`
  ADD PRIMARY KEY (`username`,`db_name`,`table_name`);

--
-- Indexes for table `pma__tracking`
--
ALTER TABLE `pma__tracking`
  ADD PRIMARY KEY (`db_name`,`table_name`,`version`);

--
-- Indexes for table `pma__userconfig`
--
ALTER TABLE `pma__userconfig`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `pma__usergroups`
--
ALTER TABLE `pma__usergroups`
  ADD PRIMARY KEY (`usergroup`,`tab`,`allowed`);

--
-- Indexes for table `pma__users`
--
ALTER TABLE `pma__users`
  ADD PRIMARY KEY (`username`,`usergroup`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pma__bookmark`
--
ALTER TABLE `pma__bookmark`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pma__column_info`
--
ALTER TABLE `pma__column_info`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pma__export_templates`
--
ALTER TABLE `pma__export_templates`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pma__history`
--
ALTER TABLE `pma__history`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pma__pdf_pages`
--
ALTER TABLE `pma__pdf_pages`
  MODIFY `page_nr` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pma__savedsearches`
--
ALTER TABLE `pma__savedsearches`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- Database: `security_services_condonuity`
--
CREATE DATABASE IF NOT EXISTS `security_services_condonuity` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `security_services_condonuity`;

-- --------------------------------------------------------

--
-- Table structure for table `approved_tags`
--

CREATE TABLE `approved_tags` (
  `id` int(11) NOT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  `support_user_id` int(11) DEFAULT NULL,
  `tag_name` int(11) DEFAULT NULL,
  `tag_status` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `bidding_products`
--

CREATE TABLE `bidding_products` (
  `id` int(11) NOT NULL,
  `product_type` int(11) NOT NULL,
  `project_id` int(11) DEFAULT NULL,
  `vendor_id` int(11) NOT NULL,
  `bidding_id` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `price` varchar(20) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bidding_products`
--

INSERT INTO `bidding_products` (`id`, `product_type`, `project_id`, `vendor_id`, `bidding_id`, `description`, `quantity`, `unit`, `price`, `created_at`, `modified_date`) VALUES
(1, 1, 1, 1, 1, 'Paint product', 10, 'litre', '10000', '2019-11-08 10:00:09', '2019-11-08 10:00:09'),
(2, 2, 1, 1, 1, 'employee', 5, 'Man Power', '100', '2019-11-08 10:00:09', '2019-11-08 10:00:09'),
(3, 1, 8, 1, 2, 'Paint product', 10, 'litre', '10000', '2019-11-08 10:02:20', '2019-11-08 10:02:20'),
(4, 2, 8, 1, 2, 'employee', 5, 'Man Power', '100', '2019-11-08 10:02:20', '2019-11-08 10:02:20');

-- --------------------------------------------------------

--
-- Table structure for table `bids`
--

CREATE TABLE `bids` (
  `bid_id` int(11) NOT NULL,
  `vendor_id` int(11) NOT NULL,
  `project_id` varchar(255) DEFAULT '''NULL''',
  `client_id` int(11) NOT NULL DEFAULT 0,
  `vendor_start_date` datetime NOT NULL,
  `vendor_completion_date` datetime NOT NULL,
  `vendor_project_duration` varchar(10) NOT NULL,
  `in_scope` varchar(255) DEFAULT '''NULL''',
  `out_of_scope` varchar(255) DEFAULT '''NULL''',
  `pre_requisite` varchar(255) DEFAULT '''NULL''',
  `reason_for_choose` varchar(255) DEFAULT '''NULL''',
  `bid_price` varchar(20) NOT NULL,
  `bid_status` int(11) NOT NULL,
  `modified_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bids`
--

INSERT INTO `bids` (`bid_id`, `vendor_id`, `project_id`, `client_id`, `vendor_start_date`, `vendor_completion_date`, `vendor_project_duration`, `in_scope`, `out_of_scope`, `pre_requisite`, `reason_for_choose`, `bid_price`, `bid_status`, `modified_date`, `created_at`) VALUES
(1, 1, '1', 1, '2019-11-11 00:00:00', '2019-12-12 00:00:00', '1 month', 'Project bid includes material, labour and full installation', 'Any item not included in this bid considered out of scope', 'need enough space to work on site', 'Great reviews from previous job and great track record', '$10000', 12, '2019-11-08 10:00:09', '2019-11-08 10:00:09'),
(2, 1, '8', 1, '2019-11-11 00:00:00', '2019-12-12 00:00:00', '1 month', 'Project bid includes material, labour and full installation', 'Any item not included in this bid considered out of scope', 'need enough space to work on site', 'Great reviews from previous job and great track record', '$10000', 13, '2019-11-08 10:03:06', '2019-11-08 10:02:20');

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `client_id` int(11) NOT NULL,
  `user_type` int(11) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `legal_name` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `country_code` int(11) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` mediumtext DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT 0,
  `organisation_id` int(11) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_role` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`client_id`, `user_type`, `first_name`, `last_name`, `legal_name`, `created_at`, `country_code`, `city`, `email`, `phone`, `status`, `organisation_id`, `password`, `user_id`, `user_role`, `username`) VALUES
(1, 12, '', '', '', '2019-10-25 07:30:14', 0, '', 'prakash@clds.com', '', 20, NULL, NULL, NULL, NULL, NULL),
(2, 1, 'k@gmail.com', '1', '12345', '2019-10-22 12:27:57', 323, '', 'user@clds.com', '1.0', 20, NULL, NULL, NULL, NULL, NULL),
(5, 1, 'Tester', 'T', 'Tester', '2019-10-23 14:29:42', 91, 'chennai', 'prakash@gmail.com', '123444', 20, NULL, NULL, NULL, NULL, NULL),
(6, 2, 'Tester', 'T', 'Tester', '2019-10-24 14:16:48', 91, 'chennai', 'test@gmail.com', '123444', 20, NULL, NULL, NULL, NULL, NULL),
(8, NULL, '', '', '', '2019-10-17 15:19:24', 0, '', 'test1@gmail.com', '', 20, NULL, NULL, NULL, NULL, NULL),
(9, NULL, '', '', '', '2019-10-17 15:19:28', 0, '', 'test12@gmail.com', '', 20, NULL, NULL, NULL, NULL, NULL),
(10, NULL, '', '', '', '2019-10-22 08:05:19', 0, '', 'test2@gmail.com', '', 20, NULL, NULL, NULL, NULL, NULL),
(11, NULL, '', '', '', '2019-10-15 09:59:58', 0, '', 'test3@gmail.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(12, 1, 'vel', 'raj', 'user', '2019-10-23 11:13:34', 91, 'chennai', 'test4@gmail.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(13, NULL, '', '', '', '2019-10-15 10:07:17', 0, '', 'test5@gmail.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(14, NULL, '', '', '', '2019-10-15 10:10:52', 0, '', 'test6@gmail.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(15, NULL, 'Prakash', 'M', 'Prakash', '2019-10-17 13:46:06', 1, 'Chennai', 'akash@gmail.com', '123434', 0, NULL, NULL, NULL, NULL, NULL),
(16, 1, 'Ram', 'R', 'Ram', '2019-10-17 14:21:27', 1, 'Chennai', 'ram@gmail.com', '123434', 20, NULL, NULL, NULL, NULL, NULL),
(17, 1, 'Sree', 'P', 'Sreeja', '2019-10-21 12:59:07', 1, 'Chennai', 'new1@gmail.com', '123434', 20, NULL, NULL, NULL, NULL, NULL),
(21, 1, '', '', '', '2019-10-21 10:22:05', 0, '', 'test@vendor.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(25, 1, '', '', '', '2019-10-21 13:45:43', 0, '', 'temp@gmail.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(26, 1, '', '', '', '2019-10-21 13:48:42', 0, '', 'temp1@gmail.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(27, 1, '', '', '', '2019-10-21 13:58:53', 0, '', 'google@gmail.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(28, 1, '', '', '', '2019-10-21 14:01:39', 0, '', 'google@google.co.in', '', 0, NULL, NULL, NULL, NULL, NULL),
(29, 1, '', '', '', '2019-10-22 07:33:08', 0, '', 'deancmiller@ambilling.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(30, 1, '', '', '', '2019-10-22 09:29:13', 0, '', 'admin@sec.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(31, 1, '', '', '', '2019-10-22 09:29:51', 0, '', 'admin@admin.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(32, 1, 'Tester', 'T', 'Tester', '2019-10-22 11:12:52', 91, 'chennai', '', '123444', 0, NULL, NULL, NULL, NULL, NULL),
(33, 1, '', '', '', '2019-10-22 12:04:25', 0, '', 'temp2@gmail.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(34, 1, '', '', '', '2019-10-22 12:05:14', 0, '', 'tzfasdfr@gmail.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(35, 1, '', '', '', '2019-10-22 12:17:21', 0, '', 'testing@gmail.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(36, 1, '', '', '', '2019-10-22 12:18:06', 0, '', 'testinggg@gmail.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(37, 1, '', '', '', '2019-10-22 12:19:01', 0, '', 'testingggggg@gmail.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(38, 1, '', '', '', '2019-10-22 12:20:13', 0, '', 'testingggggggggggg@gmail.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(39, 1, '', '', '', '2019-10-22 12:22:20', 0, '', 'testinggghttggggggggg@gmail.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(40, 1, '', '', '', '2019-10-23 10:07:49', 0, '', 'tydtemp1@gmail.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(41, 1, '', '', '', '2019-10-23 10:25:32', 0, '', 'temdasfafp1@gmail.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(42, 1, '', '', '', '2019-10-23 10:26:20', 0, '', 'temp111@gmail.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(43, 1, '', '', '', '2019-10-23 10:26:32', 0, '', 'temp111111@gmail.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(44, 1, '', '', '', '2019-10-23 10:28:37', 0, '', 'temp01@gmail.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(45, 1, '', '', '', '2019-10-23 10:28:37', 0, '', 'temp100@gmail.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(46, 1, '', '', '', '2019-10-23 10:38:05', 0, '', 'temp05451@gmail.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(47, 1, '', '', '', '2019-10-23 10:40:12', 0, '', 'temp054fsdf51@gmail', '', 0, NULL, NULL, NULL, NULL, NULL),
(48, 1, '', '', '', '2019-10-23 10:41:37', 0, '', 'temp0srt54fsdf51', '', 0, NULL, NULL, NULL, NULL, NULL),
(49, 1, '', '', '', '2019-10-23 10:42:24', 0, '', 'temp12345', '', 0, NULL, NULL, NULL, NULL, NULL),
(50, 1, '', '', '', '2019-10-23 10:53:05', 0, '', 'test11@gmail.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(51, 1, '', '', '', '2019-10-23 10:53:06', 0, '', 'test13@gmail.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(52, 1, '', '', '', '2019-10-23 11:05:36', 0, '', 'test33@gmail.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(53, 1, '', '', '', '2019-10-23 11:10:34', 0, '', 'test66@gmail.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(54, 1, '', '', '', '2019-10-23 11:10:35', 0, '', 'test67@gmail.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(55, 1, '', '', '', '2019-10-23 11:10:35', 0, '', 'test68@gmail.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(56, 1, '', '', '', '2019-10-24 11:28:48', 0, '', 'temp0@gmail.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(57, 1, '', '', '', '2019-10-24 11:28:49', 0, '', 'temp10@gmail.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(58, 1, '', '', '', '2019-10-24 11:28:49', 0, '', 'temptest@gmail.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(59, 1, '', '', '', '2019-10-24 14:12:46', 0, '', 'tempt@gmail.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(60, 1, '', '', '', '2019-10-24 14:14:57', 0, '', 'temptt@gmail.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(61, 1, '', '', '', '2019-10-24 14:15:18', 0, '', 'tempteserwert@gmail.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(62, 1, '', '', '', '2019-10-24 14:15:18', 0, '', 'temptrwert@gmail.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(63, 1, '', '', '', '2019-10-25 07:49:25', 0, '', 'magnil@gmail.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(64, 1, 'Google', 'Google', '', '2019-10-25 07:47:27', 0, '', 'admin@billflash.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(65, 1, 'Fox', 'Cs', '', '2019-10-25 07:49:29', 0, '', 'googfl@google', '', 0, NULL, NULL, NULL, NULL, NULL),
(66, 1, 'Test', 'Test', '', '2019-10-25 07:51:34', 0, '', '123', '', 0, NULL, NULL, NULL, NULL, NULL),
(67, 1, 'Google', 'Goolr', '', '2019-10-25 07:59:43', 0, '', 'google@google.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(68, 1, '', '', '', '2019-11-01 10:36:13', 0, '', 'gokul@vendor.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(69, 1, '', '', '', '2019-11-01 12:15:33', 0, '', 'google@vendor.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(70, 1, '', '', '', '2019-11-01 12:23:08', 0, '', 'testing@vendor.com', '', 0, NULL, NULL, NULL, NULL, NULL),
(71, 1, 'nov', 'n', 'November', '2019-11-13 08:01:26', 91, 'theni', 'november@gmail.com', NULL, 20, 1, '12345', NULL, 1, 'nvmbr');

-- --------------------------------------------------------

--
-- Table structure for table `client_association`
--

CREATE TABLE `client_association` (
  `id` int(11) NOT NULL,
  `organisation_id` int(11) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `user_role` int(11) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client_association`
--

INSERT INTO `client_association` (`id`, `organisation_id`, `client_id`, `user_type`, `user_role`, `status`, `created_at`) VALUES
(1, 1, 1, 1, 1, 11, '2019-10-25 07:29:51'),
(2, 1, 2, 1, 0, 11, '2019-10-22 12:27:57'),
(3, 1, 5, 1, 0, 11, '2019-10-23 14:29:42'),
(4, 16, 1, 1, 1, 11, '2019-10-22 10:59:02'),
(5, 1, 8, 0, 0, 11, '2019-10-24 10:26:31'),
(6, 1, 9, 0, 0, 10, '2019-10-17 15:23:02'),
(7, 1, 10, 1, 1, 11, '2019-10-22 08:05:19'),
(8, 122, 6, 1, 3, 11, '2019-10-24 13:23:15'),
(9, 1, 12, 0, 0, 10, '2019-10-17 15:23:11'),
(10, 1, 13, 0, 0, 11, '2019-10-17 15:23:14'),
(11, 1, 14, 1, 2, 10, '2019-10-17 15:23:17'),
(12, 1, 15, 1, 1, 11, '2019-10-17 13:46:06'),
(13, 1, 16, 1, 1, 11, '2019-10-17 13:50:01'),
(14, 41, 17, 1, 2, 11, '2019-10-21 12:59:41'),
(15, 20, 1, 1, 2, 11, '2019-10-24 08:36:53'),
(16, 1, 21, 1, 2, 10, '2019-10-21 10:22:05'),
(17, 0, 26, 1, 2, 10, '2019-10-21 13:48:42'),
(18, 0, 27, 1, 2, 10, '2019-10-21 13:58:53'),
(19, 0, 28, 1, 2, 10, '2019-10-21 14:01:39'),
(20, 44, 29, 1, 2, 10, '2019-10-22 11:03:46'),
(21, 0, 30, 1, 2, 10, '2019-10-22 09:29:13'),
(22, 0, 31, 1, 2, 10, '2019-10-22 09:29:51'),
(23, 90, 32, 1, 2, 10, '2019-10-24 08:02:23'),
(24, 0, 33, 1, 2, 10, '2019-10-22 12:04:25'),
(25, 0, 34, 1, 2, 10, '2019-10-22 12:05:14'),
(26, 0, 35, 1, 2, 10, '2019-10-22 12:17:21'),
(27, 0, 36, 1, 2, 10, '2019-10-22 12:18:06'),
(28, 0, 37, 1, 2, 10, '2019-10-22 12:19:01'),
(29, 0, 38, 1, 2, 10, '2019-10-22 12:20:13'),
(30, 0, 39, 1, 2, 10, '2019-10-22 12:22:20'),
(31, 0, 40, 1, 2, 10, '2019-10-23 10:07:49'),
(32, 0, 41, 1, 2, 10, '2019-10-23 10:25:32'),
(33, 0, 42, 1, 2, 10, '2019-10-23 10:26:20'),
(34, 0, 43, 1, 2, 10, '2019-10-23 10:26:32'),
(35, 0, 44, 1, 2, 10, '2019-10-23 10:28:37'),
(36, 0, 45, 1, 2, 10, '2019-10-23 10:28:37'),
(37, 0, 46, 1, 2, 10, '2019-10-23 10:38:05'),
(38, 0, 47, 1, 2, 10, '2019-10-23 10:40:12'),
(39, 0, 48, 1, 2, 10, '2019-10-23 10:41:37'),
(40, 0, 49, 1, 2, 10, '2019-10-23 10:42:24'),
(41, 0, 50, 1, 2, 10, '2019-10-23 10:53:05'),
(42, 0, 51, 1, 2, 10, '2019-10-23 10:53:06'),
(43, 0, 52, 1, 2, 10, '2019-10-23 11:05:36'),
(44, 0, 53, 1, 2, 10, '2019-10-23 11:10:34'),
(45, 0, 54, 1, 2, 10, '2019-10-23 11:10:35'),
(46, 0, 55, 1, 2, 10, '2019-10-23 11:10:35'),
(47, 0, 56, 1, 2, 10, '2019-10-24 11:28:48'),
(48, 0, 57, 1, 2, 10, '2019-10-24 11:28:49'),
(49, 0, 58, 1, 2, 10, '2019-10-24 11:28:49'),
(50, 0, 59, 1, 2, 10, '2019-10-24 14:12:46'),
(51, 0, 60, 1, 2, 10, '2019-10-24 14:14:57'),
(52, 0, 61, 1, 2, 10, '2019-10-24 14:15:18'),
(53, 0, 62, 1, 2, 10, '2019-10-24 14:15:18'),
(54, 6, 63, 1, 4, 10, '2019-10-25 07:49:46'),
(55, 1, 64, 1, 1, 10, '2019-10-25 07:47:27'),
(56, 1, 65, 1, 0, 10, '2019-10-25 07:49:29'),
(57, 1, 66, 1, 0, 10, '2019-10-25 07:51:34'),
(58, 1, 67, 1, 1, 10, '2019-10-25 07:59:43'),
(59, 0, 68, 1, 0, 10, '2019-11-01 10:36:13'),
(60, 0, 69, 1, 0, 10, '2019-11-01 12:15:33'),
(61, 0, 70, 1, 0, 10, '2019-11-01 12:23:08');

-- --------------------------------------------------------

--
-- Table structure for table `client_notification_settings`
--

CREATE TABLE `client_notification_settings` (
  `client_notification_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `alert_new_post` int(11) DEFAULT NULL,
  `alert_account_change` int(11) DEFAULT NULL,
  `alert_project_award` int(11) DEFAULT NULL,
  `alert_que_ans` int(11) DEFAULT NULL,
  `alert_review_change` int(11) DEFAULT NULL,
  `alert_new_bid` int(11) DEFAULT NULL,
  `alert_bid_end_date` int(11) DEFAULT NULL,
  `alert_contract_expiry` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `modified_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `client_repository`
--

CREATE TABLE `client_repository` (
  `id` int(11) NOT NULL,
  `created_client_id` int(11) DEFAULT NULL,
  `leasee_client_id` int(11) DEFAULT NULL,
  `owner_name` varchar(255) DEFAULT NULL,
  `lease_info` varchar(255) DEFAULT NULL,
  `lien_info` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `repository_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `client_tasks`
--

CREATE TABLE `client_tasks` (
  `id` int(11) NOT NULL,
  `task_name` varchar(255) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `created_client_id` int(11) DEFAULT NULL,
  `assignee_client_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `open_date` datetime DEFAULT NULL,
  `closed_date` datetime DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `client_task_files`
--

CREATE TABLE `client_task_files` (
  `id` int(11) NOT NULL,
  `task_id` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `company_documents_verification`
--

CREATE TABLE `company_documents_verification` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `support_user_id` int(11) DEFAULT NULL,
  `document_type` varchar(255) DEFAULT NULL,
  `document_name` varchar(255) DEFAULT NULL,
  `vertification_status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `contract_insurance`
--

CREATE TABLE `contract_insurance` (
  `insurance_id` int(11) NOT NULL,
  `contract_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `provider` varchar(255) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `contract_types`
--

CREATE TABLE `contract_types` (
  `id` int(11) NOT NULL,
  `type` varchar(255) DEFAULT 'NULL',
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `contract_types`
--

INSERT INTO `contract_types` (`id`, `type`, `name`) VALUES
(1, '1', 'Fixed cost'),
(2, '2', 'Time & Material'),
(3, '3', 'Annual Contract');

-- --------------------------------------------------------

--
-- Table structure for table `country`
--

CREATE TABLE `country` (
  `id` int(11) NOT NULL,
  `code` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `continent_name` varchar(255) DEFAULT NULL,
  `currency_code` varchar(20) NOT NULL,
  `currency` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `country`
--

INSERT INTO `country` (`id`, `code`, `name`, `continent_name`, `currency_code`, `currency`) VALUES
(1, 91, 'INDIA', NULL, 'Rs', 'Rupees'),
(2, 966, 'SAUDI ARABIA', NULL, '', 'SAR'),
(3, 1, 'CANADA', NULL, '$', 'Dollar'),
(4, 65, 'SINGAPORE', NULL, '', ''),
(5, 1, 'USA', NULL, '$', 'Dollar');

-- --------------------------------------------------------

--
-- Table structure for table `group_settings`
--

CREATE TABLE `group_settings` (
  `id` int(11) NOT NULL,
  `user_type` int(11) DEFAULT NULL,
  `max_count` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `group_settings`
--

INSERT INTO `group_settings` (`id`, `user_type`, `max_count`) VALUES
(1, 1, 12),
(2, 2, 12);

-- --------------------------------------------------------

--
-- Table structure for table `insurance`
--

CREATE TABLE `insurance` (
  `insurance_id` int(11) NOT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `provider` varchar(255) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `message_board`
--

CREATE TABLE `message_board` (
  `message_board_id` int(11) NOT NULL,
  `type` int(11) DEFAULT NULL,
  `topic_name` varchar(255) DEFAULT NULL,
  `topic_comment` varchar(255) DEFAULT NULL,
  `attachments` varchar(255) DEFAULT NULL,
  `created_user_id` int(11) DEFAULT NULL,
  `created_user_type` int(11) DEFAULT NULL,
  `target_user_id` int(11) DEFAULT NULL,
  `target_user_type` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `message_board_comments`
--

CREATE TABLE `message_board_comments` (
  `id` int(11) NOT NULL,
  `message_board_id` int(11) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `attachments` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `message_board_type`
--

CREATE TABLE `message_board_type` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `organisation`
--

CREATE TABLE `organisation` (
  `id` int(11) NOT NULL,
  `user_type` int(11) DEFAULT NULL,
  `organisation_name` varchar(255) DEFAULT NULL,
  `corporate_number` varchar(255) DEFAULT 'NULL',
  `admin_email` varchar(255) NOT NULL,
  `general_email` varchar(255) NOT NULL,
  `management_email` varchar(255) NOT NULL,
  `board_email` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT 'NULL',
  `city` varchar(255) DEFAULT 'NULL',
  `province` varchar(255) DEFAULT 'NULL',
  `postal_code` varchar(255) DEFAULT 'NULL',
  `country_code` int(11) DEFAULT 0,
  `phone_number` varchar(255) DEFAULT 'NULL',
  `fax_number` varchar(255) DEFAULT 'NULL',
  `management_company` varchar(255) DEFAULT NULL,
  `registration_date` date DEFAULT NULL,
  `units` int(11) DEFAULT NULL,
  `voting_units` int(11) DEFAULT NULL,
  `manager_name` varchar(255) NOT NULL,
  `manager_email` varchar(255) NOT NULL,
  `manager_phone` varchar(40) NOT NULL,
  `status` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `organisation_id` int(11) NOT NULL,
  `user_role` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `organisation`
--

INSERT INTO `organisation` (`id`, `user_type`, `organisation_name`, `corporate_number`, `admin_email`, `general_email`, `management_email`, `board_email`, `address`, `city`, `province`, `postal_code`, `country_code`, `phone_number`, `fax_number`, `management_company`, `registration_date`, `units`, `voting_units`, `manager_name`, `manager_email`, `manager_phone`, `status`, `created_at`, `organisation_id`, `user_role`) VALUES
(1, 1, 'global 4 organisations', 'corp12345', 'prakash@clds.com', 'general1@clds.com', 'management1@clds.com', 'board1@clds.com', 'chennai4', 'TN', 'IN', '400042', 0, '42325394', 'FX1444444', 'New Management', '2019-01-01', 350, 500, 'Ramalingam', 'manager@newOrg.com', '9500095000', 12, '2019-10-31 10:11:21', 0, NULL),
(16, 1, 'Public org', 'corp1232', 'test@gmail.com', '', '', '', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, '', '', '0', 5, '2019-10-15 10:58:02', 0, NULL),
(18, 1, 'Public org', 'corp12323', 'test1@gmail.com', '', '', '', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, '', '', '0', 5, '2019-10-15 11:08:31', 0, NULL),
(20, 1, 'Public org', 'corp12343', 'test1@gmail.com', '', '', '', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, '', '', '0', 5, '2019-10-15 11:10:56', 0, NULL),
(23, 1, 'Public org', 'corp12333', 'test1@gmail.com', '', '', '', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, '', '', '0', 5, '2019-10-15 11:13:21', 0, NULL),
(25, 1, 'global 4 organisation', 'corp12326', 'test@gmail.com', 'general1@clds.com', 'management1@clds.com', 'board1@clds.com', 'chennai4', 'TN', 'TN', '400042', 91, '42325394', 'FX1444444', 'Org management', '2019-03-03', 70, 7, '', '', '0', 5, '2019-11-01 06:55:54', 0, NULL),
(32, 1, 'string', '12344545', 'test@gmail.com', '', '', '', 'test', 'chennai', 'string', 'string', 1, 'string', 'string', 'string', '2019-10-10', 10, 5, '', '', '0', 12, '2019-10-21 10:43:36', 0, NULL),
(36, 1, 'Public org', 'corp45454', 'new@gmail.com', '', '', '', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, '', '', '0', 12, '2019-10-21 12:27:42', 0, NULL),
(37, 1, 'Public org', 'corp454546', 'new1@gmail.com', '', '', '', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, '', '', '0', 12, '2019-10-21 12:29:09', 0, NULL),
(38, 1, 'Public org', 'corp46767676', 'new1@gmail.com', '', '', '', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, '', '', '0', 12, '2019-10-21 12:38:07', 0, NULL),
(39, 1, 'Public org', 'erere', 'new1@gmail.com', '', '', '', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, '', '', '0', 12, '2019-10-21 12:56:08', 0, NULL),
(40, 1, 'Public org', '343434', 'new1@gmail.com', '', '', '', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, '', '', '0', 12, '2019-10-21 12:57:18', 0, NULL),
(41, 1, 'Public org', '345345345', 'new1@gmail.com', '', '', '', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, '', '', '0', 12, '2019-10-21 12:59:41', 0, NULL),
(42, 1, 'sdfsdf', 'sdfs', 'deancmiller@ambilling.com', '', '', '', 'sdfs', 'dfsdf', 'sdfs', 'dfsdf', 0, '', '', '', NULL, 0, 0, '', '', '', 12, '2019-10-22 10:53:09', 0, NULL),
(43, 1, '', '', 'deancmiller@ambilling.com', '', '', '', '', '', '', '', 0, '', '', '', NULL, 0, 0, '', '', '', 12, '2019-10-22 10:53:56', 0, NULL),
(44, 1, 'test', '4654564', 'deancmiller@ambilling.com', 'google@google.com', 'adjaksb@jndsjf', 'jdnf@jnfdjks', '4564', '564', '5646', '54', 0, '65654', '5645656465', '78', '2019-10-07', 546, 786, '789', '789', '789', 12, '2019-10-22 11:03:46', 0, NULL),
(45, 1, 'Public org', 'corp12323342432', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 10:57:57', 0, NULL),
(46, 1, 'Public org', 'corp1232123', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 10:58:37', 0, NULL),
(47, 1, 'Public org', 'corp991', 'test991@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:01:20', 0, NULL),
(48, 1, 'Public org', 'corp992', '', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:01:20', 0, NULL),
(49, 1, 'Public org', 'corp993', 'test992@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:01:21', 0, NULL),
(50, 1, 'Public org', 'corp995', 'test992@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:01:21', 0, NULL),
(51, 1, 'Public org', 'corp996', 'test996@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:01:21', 0, NULL),
(52, 1, 'Public org', 'corp997', 'test997@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', '', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:01:21', 0, NULL),
(53, 1, 'Public org', 'corp998', 'test998@gmail.com', '', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:01:22', 0, NULL),
(54, 1, 'Public org', 'corp999', 'test999@gmail.com', 'general@clds.com', '', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:01:22', 0, NULL),
(55, 1, 'Public org', 'corp9910', 'test9910@gmail.com', 'general@clds.com', 'management@clds.com', '', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:01:22', 0, NULL),
(56, 1, 'Public org', 'corp9911', 'test9911@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', '', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:01:22', 0, NULL),
(57, 1, 'Public org', 'corp9912', 'test9912@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 0, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:01:22', 0, NULL),
(58, 1, 'Public org', 'corp9913', 'test9913@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', '', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:01:22', 0, NULL),
(59, 1, 'Public org', 'corp9914', 'test9914@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', '', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:01:22', 0, NULL),
(60, 1, '', 'corp9915', 'test9915@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:01:22', 0, NULL),
(61, 1, 'Public org', 'corp9916', 'test9916@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:01:22', 0, NULL),
(62, 1, 'Public org', 'corp9917', 'test9917@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:01:22', 0, NULL),
(63, 1, 'Public org', 'corp9918', 'test9918@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', '', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:01:23', 0, NULL),
(64, 1, 'Public org', 'corp99111', 'test9920@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 0, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:01:23', 0, NULL),
(65, 1, 'Public org', 'corp99112', 'test9921@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 0, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:01:23', 0, NULL),
(66, 1, 'Public org', 'corp99113', 'test9922@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, '', 'ram@clds.com', '1222323', 12, '2019-10-23 12:01:23', 0, NULL),
(67, 1, 'Public org', 'corp99114', 'test9923@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', '', '1222323', 12, '2019-10-23 12:01:23', 0, NULL),
(68, 1, 'Public org', 'corp99115', 'test9924@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '', 12, '2019-10-23 12:01:23', 0, NULL),
(69, 1, 'Public org', 'corp99116', 'test9925@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:01:23', 0, NULL),
(70, 1, 'Public org', 'corp99117', 'test9926@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:01:24', 0, NULL),
(71, 1, 'Public org', 'corp99118', 'test9927@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:01:24', 0, NULL),
(72, 1, 'Public org', 'corp99119', 'test9928@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:01:24', 0, NULL),
(73, 1, 'Public org', 'corp99120', 'test9929@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:01:24', 0, NULL),
(74, 1, 'Public org', 'corp99121', 'test99210@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:01:24', 0, NULL),
(75, 1, 'Public org', 'corp99122', 'test99211@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:01:24', 0, NULL),
(76, 1, 'Public org', 'corp99123', 'test99212@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:01:24', 0, NULL),
(77, 1, 'Public org', 'corp99124', 'test99213@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:01:24', 0, NULL),
(78, 1, 'Public org', 'corp99125', 'test99214@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:01:24', 0, NULL),
(79, 1, 'Public org', 'corp99126', 'test99215@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:01:24', 0, NULL),
(80, 1, 'Public org', 'corp99127', 'test99216@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:01:25', 0, NULL),
(81, 1, 'Public org', 'corp99128', 'test99217@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:01:25', 0, NULL),
(82, 1, 'Public org', 'corp12327837239238231237', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:10:24', 0, NULL),
(83, 1, 'Public org', 'corp12323463645756789', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:10:58', 0, NULL),
(84, 1, 'Public org', 'corp123264675475778', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:11:52', 0, NULL),
(85, 1, 'Public org', 'corp1232548948848', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-23 12:12:31', 0, NULL),
(86, 1, 'Public org', 'corp99999', 'test9999@gmail.com', 'general9999@clds.com', 'managemen9999t@clds.com', 'board9999@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram999@clds.com', '1222323', 12, '2019-10-23 12:23:35', 0, NULL),
(87, 1, 'Public org', 'corp12326564564564123', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-24 06:48:23', 0, NULL),
(88, 1, 'Public org', 'corp87988998', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-24 06:51:41', 0, NULL),
(89, 1, 'Public org', 'corp531', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-24 08:02:22', 0, NULL),
(90, 1, 'Public org', 'corp532', '', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-24 08:02:23', 0, NULL),
(91, 1, 'Public org', 'corp533', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-24 08:02:23', 0, NULL),
(92, 1, 'Public org', 'corp534', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-24 08:02:24', 0, NULL),
(93, 1, 'Public org', 'corp535', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', '', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-24 08:02:24', 0, NULL),
(94, 1, 'Public org', 'corp536', 'test@gmail.com', '', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-24 08:02:24', 0, NULL),
(95, 1, 'Public org', 'corp537', 'test@gmail.com', 'general@clds.com', '', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-24 08:02:25', 0, NULL),
(96, 1, 'Public org', 'corp538', 'test@gmail.com', 'general@clds.com', 'management@clds.com', '', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-24 08:02:25', 0, NULL),
(97, 1, 'Public org', 'corp539', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', '', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-24 08:02:25', 0, NULL),
(98, 1, 'Public org', 'corp5310', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 0, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-24 08:02:25', 0, NULL),
(99, 1, 'Public org', 'corp5311', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', '', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-24 08:02:25', 0, NULL),
(100, 1, 'Public org', 'corp5312', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', '', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-24 08:02:25', 0, NULL),
(101, 1, '', 'corp5313', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-24 08:02:25', 0, NULL),
(102, 1, 'Public org', 'corp5314', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-24 08:02:26', 0, NULL),
(103, 1, 'Public org', 'corp5315', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-24 08:02:26', 0, NULL),
(104, 1, 'Public org', 'corp5316', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', '', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-24 08:02:26', 0, NULL),
(105, 1, 'Public org', 'corp5318', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 0, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-24 08:02:27', 0, NULL),
(106, 1, 'Public org', 'corp5319', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 0, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-24 08:02:27', 0, NULL),
(107, 1, 'Public org', 'corp53110', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, '', 'ram@clds.com', '1222323', 12, '2019-10-24 08:02:27', 0, NULL),
(108, 1, 'Public org', 'corp53111', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', '', '1222323', 12, '2019-10-24 08:02:27', 0, NULL),
(109, 1, 'Public org', 'corp53112', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '', 12, '2019-10-24 08:02:27', 0, NULL),
(110, 1, 'Public org', 'corp53113', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-24 08:02:27', 0, NULL),
(111, 1, 'Public org', 'corp53114', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-24 08:02:27', 0, NULL),
(112, 1, 'Public org', 'corp53115', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-24 08:02:28', 0, NULL),
(113, 1, 'Public org', 'corp53116', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-24 08:02:28', 0, NULL),
(114, 1, 'Public org', 'corp53117', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-24 08:02:28', 0, NULL),
(115, 1, 'Public org', 'corp53118', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-24 08:02:28', 0, NULL),
(116, 1, 'Public org', 'corp53119', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-24 08:02:28', 0, NULL),
(117, 1, 'Public org', 'corp53120', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-24 08:02:28', 0, NULL),
(118, 1, 'Public org', 'corp53121', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-24 08:02:28', 0, NULL),
(119, 1, 'Public org', 'corp53122', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-24 08:02:28', 0, NULL),
(120, 1, 'Public org', 'corp53123', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-24 08:02:28', 0, NULL),
(121, 1, 'Public org', 'corp53124', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-24 08:02:29', 0, NULL),
(122, 1, 'Public org', 'corp53125', 'test@gmail.com', 'general@clds.com', 'management@clds.com', 'board@clds.com', 'chennai', 'chennai', 'india', '123', 1, '1223232', 'fx123', 'public ltd', '2019-10-10', 20, 10, 'Ram', 'ram@clds.com', '1222323', 12, '2019-10-24 08:02:29', 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `organisation_amenities_info`
--

CREATE TABLE `organisation_amenities_info` (
  `id` int(11) NOT NULL,
  `organisation_id` int(11) DEFAULT NULL,
  `gym` int(11) DEFAULT NULL,
  `party_room` int(11) DEFAULT NULL,
  `elevators_count` int(11) DEFAULT NULL,
  `swim_pool` int(11) DEFAULT NULL,
  `pool_indoor` int(11) DEFAULT NULL,
  `pool_outdoor` int(11) NOT NULL,
  `parking` int(11) DEFAULT NULL,
  `parking_ground_level` int(255) DEFAULT NULL,
  `parking_under_ground` int(11) NOT NULL,
  `under_ground_parking_spots` int(11) NOT NULL,
  `ground_level_parking_spots` int(11) NOT NULL,
  `other_information` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `organisation_amenities_info`
--

INSERT INTO `organisation_amenities_info` (`id`, `organisation_id`, `gym`, `party_room`, `elevators_count`, `swim_pool`, `pool_indoor`, `pool_outdoor`, `parking`, `parking_ground_level`, `parking_under_ground`, `under_ground_parking_spots`, `ground_level_parking_spots`, `other_information`) VALUES
(1, 1, 2, 1, 2, 1, 1, 1, 2, 2, 2, 0, 0, 'Food facility available'),
(2, 16, 2, 1, 1, 2, 0, 0, 2, 0, 0, 0, 0, 'food and cab available'),
(3, 16, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 'food available'),
(4, 2222, 1, 1, 5, 1, 0, 0, 1, 0, 0, 0, 0, 'Google'),
(5, 36, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 'food available'),
(6, 37, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 'food available'),
(7, 38, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 'food available'),
(8, 39, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 'food available'),
(9, 40, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 'food available'),
(10, 41, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 'food available'),
(11, 16, 1, 1, 1, 1, 1, 2, 1, 1, 2, 0, 0, 'food available'),
(12, 16, 1, 1, 1, 1, 1, 2, 1, 1, 2, 0, 0, 'food available'),
(13, 16, 1, 1, 0, 1, 1, 2, 1, 1, 2, 0, 0, 'food available'),
(14, 16, 0, 1, 1, 1, 1, 2, 1, 1, 2, 0, 0, 'food available'),
(15, 0, 1, 1, 1, 1, 1, 2, 1, 1, 2, 0, 0, 'food available'),
(16, 16, 1, 1, 1, 1, 1, 2, 1, 1, 2, 0, 0, ''),
(17, 16, 1, 1, 1, 1, 1, 2, 0, 1, 2, 0, 0, 'food available'),
(18, 16, 1, 1, 1, 1, 1, 2, 1, 1, 2, 0, 0, 'food available'),
(19, 16, 1, 1, 1, 1, 1, 2, 1, 0, 2, 0, 0, 'food available'),
(20, 16, 1, 1, 1, 1, 1, 2, 1, 1, 0, 0, 0, 'food available'),
(21, 16, 1, 0, 1, 1, 1, 2, 1, 1, 2, 0, 0, 'food available'),
(22, 16, 1, 1, 1, 1, 0, 2, 1, 1, 2, 0, 0, 'food available'),
(23, 16, 1, 1, 1, 1, 1, 0, 1, 1, 2, 0, 0, 'food available'),
(24, 16, 1, 1, 1, 1, 1, 2, 1, 1, 2, 0, 0, 'food available'),
(25, 16, 1, 1, 1, 1, 1, 2, 1, 1, 2, 0, 0, 'food available'),
(26, 16, 1, 1, 1, 0, 1, 2, 1, 1, 2, 0, 0, 'food available'),
(27, 17, 3, 1, 2, 1, 1, 2, 1, 1, 2, 0, 0, 'food available');

-- --------------------------------------------------------

--
-- Table structure for table `organisation_billing_address`
--

CREATE TABLE `organisation_billing_address` (
  `id` int(11) NOT NULL,
  `organisation_id` int(11) DEFAULT NULL,
  `street_address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `organisation_payment_card_details`
--

CREATE TABLE `organisation_payment_card_details` (
  `id` int(11) NOT NULL,
  `organisation_id` int(11) DEFAULT NULL,
  `card_number` varchar(255) DEFAULT NULL,
  `name_on_card` varchar(255) DEFAULT NULL,
  `expiry_date` varchar(255) DEFAULT NULL,
  `security_code` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `predefined_tags`
--

CREATE TABLE `predefined_tags` (
  `tag_id` int(11) NOT NULL,
  `tag_name` int(11) DEFAULT NULL,
  `tag_status` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `projects`
--

CREATE TABLE `projects` (
  `project_id` int(11) NOT NULL,
  `client_id` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `project_name` varchar(255) DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  `bid_end_date` datetime DEFAULT NULL,
  `project_start_date` datetime DEFAULT NULL,
  `project_completion_deadline` datetime DEFAULT NULL,
  `estimated_budget` varchar(20) DEFAULT NULL,
  `duration` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `special_conditions` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `contract_type` int(11) DEFAULT NULL,
  `insurance_required` tinyint(1) DEFAULT NULL,
  `post_type` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `projects`
--

INSERT INTO `projects` (`project_id`, `client_id`, `created_at`, `modified_date`, `project_name`, `tags`, `bid_end_date`, `project_start_date`, `project_completion_deadline`, `estimated_budget`, `duration`, `description`, `special_conditions`, `city`, `contract_type`, `insurance_required`, `post_type`, `status`) VALUES
(1, 1, '2019-11-06 14:44:52', '2019-11-06 14:49:35', 'First ever project', '1,2,3', '2019-10-10 00:00:00', '2019-11-11 00:00:00', '2019-12-12 00:00:00', '$10000', '1 Month', 'Sample Project', 'No conditions', 'chennai', 1, 1, 1, 13),
(2, 1, '2019-11-07 07:24:22', '2019-11-07 07:24:22', 'New project', '1,2,3', '2019-11-11 00:00:00', '2019-11-11 00:00:00', '2019-12-12 00:00:00', '$2000', '1 month', 'this project for testing', 'no conditions', 'chennai', 1, 1, 1, 12),
(3, 1, '2019-11-07 07:27:27', '2019-11-07 07:27:27', 'New project', '1,2,3', '2019-11-11 00:00:00', '2019-11-11 00:00:00', '2019-12-12 00:00:00', '$2000', '1 month', 'this project for testing', 'no conditions', 'chennai', 1, 1, 1, 12),
(4, 1, '2019-11-07 07:27:35', '2019-11-07 10:20:58', 'New project', '1,2,3', '2019-11-11 00:00:00', '2019-11-11 00:00:00', '2019-12-12 00:00:00', '$2000', '1 month', 'this project for testing', 'no conditions', 'chennai', 1, 1, 1, 14),
(5, 1, '2019-11-07 11:17:28', '2019-11-07 11:17:28', 'New project', '1,2,3', '2019-11-11 00:00:00', '2019-11-11 00:00:00', '2019-12-12 00:00:00', '$2000', '1 month', 'this project for testing', 'no conditions', 'chennai', 1, 1, 1, 12),
(6, 1, '2019-11-07 11:19:30', '2019-11-07 11:19:30', 'New project', '1,2,3', '2019-11-11 00:00:00', '2019-11-11 00:00:00', '2019-12-12 00:00:00', '$2000', '1 month', 'this project for testing', 'no conditions', 'chennai', 1, 1, 1, 12),
(7, 1, '2019-11-07 11:20:52', '2019-11-07 11:20:52', 'New project', '1,2,3', '2019-11-11 00:00:00', '2019-11-11 00:00:00', '2019-12-12 00:00:00', '$2000', '1 month', 'this project for testing', 'no conditions', 'chennai', 1, 1, 1, 12),
(8, 1, '2019-11-07 11:22:40', '2019-11-07 11:22:40', 'New project', '1,2,3', '2019-11-11 00:00:00', '2019-11-11 00:00:00', '2019-12-12 00:00:00', '$2000', '1 month', 'this project for testing', 'no conditions', 'chennai', 1, 1, 1, 12),
(9, 1, '2019-11-07 12:13:41', '2019-11-07 12:13:41', 'New project', '1,2,3', '2019-11-11 00:00:00', '2019-11-11 00:00:00', '2019-12-12 00:00:00', '$2000', '1 month', 'this project for testing', 'no conditions', 'chennai', 1, 1, 1, 12),
(10, 1, '2019-11-07 12:20:05', '2019-11-07 12:20:05', 'New project', '1,2,3', '2019-11-11 00:00:00', '2019-11-11 00:00:00', '2019-12-12 00:00:00', '$2000', '1 month', 'this project for testing', 'no conditions', 'chennai', 1, 1, 1, 12),
(11, 1, '2019-11-07 12:20:50', '2019-11-07 12:20:50', 'New project', '1,2,3', '2019-11-11 00:00:00', '2019-11-11 00:00:00', '2019-12-12 00:00:00', '$2000', '1 month', 'this project for testing', 'no conditions', 'chennai', 1, 1, 1, 12),
(12, 1, '2019-11-07 12:21:53', '2019-11-07 12:21:53', 'New project', '1,2,3', '2019-11-11 00:00:00', '2019-11-11 00:00:00', '2019-12-12 00:00:00', '$2000', '1 month', 'this project for testing', 'no conditions', 'chennai', 1, 1, 1, 12);

-- --------------------------------------------------------

--
-- Table structure for table `project_awards`
--

CREATE TABLE `project_awards` (
  `project_awards_id` int(11) NOT NULL,
  `bid_id` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `bids_status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `project_bidding_files`
--

CREATE TABLE `project_bidding_files` (
  `bidding_file_id` int(11) NOT NULL,
  `project_id` int(11) DEFAULT NULL,
  `bidding_id` int(11) DEFAULT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `modified_sate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `project_files`
--

CREATE TABLE `project_files` (
  `id` int(11) NOT NULL,
  `project_id` int(11) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `project_products`
--

CREATE TABLE `project_products` (
  `id` int(11) NOT NULL,
  `project_id` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT 'NULL',
  `quantity` int(11) DEFAULT 0,
  `unit` varchar(255) DEFAULT 'NULL',
  `created_at` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `project_products`
--

INSERT INTO `project_products` (`id`, `project_id`, `description`, `quantity`, `unit`, `created_at`) VALUES
(1, 1, 'product 1', 10, 'litre', NULL),
(2, 1, 'Paint', 10, 'litre', NULL),
(3, 1, 'Gibsom Board', 10, 'square feet', NULL),
(4, 12, 'product 1', 10, 'litre', '2019-11-07 17:51:53'),
(5, 12, 'Paint', 10, 'litre', '2019-11-07 17:51:53'),
(6, 12, 'Gibsom Board', 10, 'square feet', '2019-11-07 17:51:53');

-- --------------------------------------------------------

--
-- Table structure for table `project_questions`
--

CREATE TABLE `project_questions` (
  `id` int(11) NOT NULL,
  `project_id` int(11) NOT NULL,
  `created_user_id` int(11) NOT NULL,
  `created_user_type` int(11) NOT NULL,
  `question` varchar(255) DEFAULT 'NULL',
  `answer` varchar(255) DEFAULT '',
  `answered_user_id` int(11) NOT NULL DEFAULT 0,
  `answered_user_type` int(11) NOT NULL DEFAULT 0,
  `created_at` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `project_questions`
--

INSERT INTO `project_questions` (`id`, `project_id`, `created_user_id`, `created_user_type`, `question`, `answer`, `answered_user_id`, `answered_user_type`, `created_at`) VALUES
(1, 1, 1, 2, 'Do you prefer any Paint Brand?', 'Yes, We prefer Asian Paint', 1, 1, '2019-11-08 16:32:46');

-- --------------------------------------------------------

--
-- Table structure for table `project_tags`
--

CREATE TABLE `project_tags` (
  `id` int(11) NOT NULL,
  `project_id` int(11) DEFAULT NULL,
  `tag_id` int(11) DEFAULT NULL,
  `tag_name` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `ratings`
--

CREATE TABLE `ratings` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `contract_id` int(11) DEFAULT NULL,
  `rating` float DEFAULT NULL,
  `rating_module_id` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `rating_modules`
--

CREATE TABLE `rating_modules` (
  `rating_module_id` int(11) NOT NULL,
  `review_module_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE `reviews` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `contract_id` int(11) DEFAULT NULL,
  `review` float DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `serviced_areas`
--

CREATE TABLE `serviced_areas` (
  `id` int(11) NOT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  `area_name` varchar(255) DEFAULT NULL,
  `area_code` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `subscription`
--

CREATE TABLE `subscription` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `subscription_type` varchar(255) DEFAULT NULL,
  `tag_status` int(11) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `expiry_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `subscription_types`
--

CREATE TABLE `subscription_types` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `validity` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `support_user`
--

CREATE TABLE `support_user` (
  `support_user_id` int(11) NOT NULL,
  `user_type` int(11) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `country_code` int(11) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` mediumtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_account`
--

CREATE TABLE `user_account` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT 'NULL',
  `user_type` int(11) DEFAULT NULL,
  `user_role` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_account`
--

INSERT INTO `user_account` (`id`, `user_id`, `username`, `password`, `user_type`, `user_role`, `created_at`) VALUES
(1, 1, 'prakash@clds.com', '0hmCKKFlbtY5BKDvdCZjBg==', 1, 1, '2019-10-15 15:09:26'),
(2, 2, 'user@clds.com', '0hmCKKFlbtY5BKDvdCZjBg==', 1, 1, '2019-09-26 12:27:55'),
(3, 5, 'prakash@gmail.com', '0hmCKKFlbtY5BKDvdCZjBg==', 1, 1, '2019-10-23 12:13:25'),
(4, 6, 'test@gmail.com', '0hmCKKFlbtY5BKDvdCZjBg==', 1, 1, '2019-10-19 10:08:47'),
(6, 8, 'test1@gmail.com', '+KknbEoN75APmgOx5LUbow==', 1, 1, '2019-10-24 13:54:59'),
(7, 9, 'test12@gmail.com', '0hmCKKFlbtY5BKDvdCZjBg==', 1, 1, '2019-10-24 13:25:15'),
(8, 10, 'test2@gmail.com', 'dEurAdnzwGhU5rnhL6NVVQ==', 1, 1, '2019-10-22 08:08:06'),
(9, 11, 'test3@gmail.com', 'svEW1abSigybilZfMnsMgA==', 0, 0, '2019-10-22 08:09:53'),
(10, 12, 'test4@gmail.com', '', 0, 0, '2019-10-15 10:08:22'),
(11, 13, 'test5@gmail.com', '', 0, 0, '2019-10-15 10:08:45'),
(12, 14, 'test6@gmail.com', '', 1, 2, '2019-10-15 10:10:52'),
(13, 1, 'user@vendor.com', 'yfQ77nMzJu+hqzb6Tp9VZA==', 2, 1, '2019-11-01 07:04:50'),
(14, 0, 'string', '0hmCKKFlbtY5BKDvdCZjBg==', 2, 1, '2019-10-24 11:08:56'),
(15, 3, 'test@vendor.com', '0hmCKKFlbtY5BKDvdCZjBg==', 2, 1, '2019-10-16 10:10:24'),
(16, 0, 'test1@vendor.com', '0hmCKKFlbtY5BKDvdCZjBg==', 2, 1, '2019-10-24 11:08:56'),
(17, 15, 'akash@gmail.com', '0hmCKKFlbtY5BKDvdCZjBg==', 1, 1, '2019-10-17 13:46:06'),
(18, 16, 'ram@gmail.com', '9qxcifsbFdHc2sktoJAF9A==', 1, 1, '2019-10-17 14:21:27'),
(19, 17, 'gokul1@gmail.com', '0hmCKKFlbtY5BKDvdCZjBg==', 1, 2, '2019-10-23 11:59:19'),
(23, 21, 'test@vendor.com', '', 1, 2, '2019-10-21 10:22:05'),
(27, 25, 'temp@gmail.com', '', 1, 2, '2019-10-21 13:45:43'),
(28, 26, 'temp1@gmail.com', '', 1, 2, '2019-10-21 13:48:42'),
(29, 0, 'newvendor@vendor.com', '0hmCKKFlbtY5BKDvdCZjBg==', 2, 2, '2019-10-24 11:08:56'),
(30, 27, 'google@gmail.com', '', 1, 2, '2019-10-21 13:58:53'),
(31, 28, 'google@google.co.in', '', 1, 2, '2019-10-21 14:01:39'),
(32, 6, 'gokul@gmail.com', '0hmCKKFlbtY5BKDvdCZjBg==', 2, 2, '2019-10-23 11:59:58'),
(33, 7, 'temp10@gmail.com', '', 2, 2, '2019-10-22 06:33:24'),
(34, 29, 'deancmiller@ambilling.com', '', 1, 2, '2019-10-22 07:33:08'),
(35, 30, 'admin@sec.com', '', 1, 2, '2019-10-22 09:29:13'),
(36, 31, 'admin@admin.com', '', 1, 2, '2019-10-22 09:29:51'),
(37, 32, '', '', 1, 2, '2019-10-22 11:12:52'),
(38, 33, 'temp2@gmail.com', '', 1, 2, '2019-10-22 12:04:25'),
(39, 34, 'tzfasdfr@gmail.com', '', 1, 2, '2019-10-22 12:05:14'),
(40, 35, 'testing@gmail.com', '', 1, 2, '2019-10-22 12:17:21'),
(41, 36, 'testinggg@gmail.com', '', 1, 2, '2019-10-22 12:18:06'),
(42, 37, 'testingggggg@gmail.com', '', 1, 2, '2019-10-22 12:19:01'),
(43, 38, 'testingggggggggggg@gmail.com', '', 1, 2, '2019-10-22 12:20:13'),
(44, 39, 'testinggghttggggggggg@gmail.com', '', 1, 2, '2019-10-22 12:22:20'),
(45, 8, 'newvendor1@vendor.com', '0hmCKKFlbtY5BKDvdCZjBg==', 2, 2, '2019-10-24 11:08:55'),
(46, 9, 'newvebkmkndor@vendor.com', '', 2, 2, '2019-10-23 10:06:39'),
(47, 10, 'newvebetkmkndor@vendor.com', '', 2, 2, '2019-10-23 10:07:09'),
(48, 40, 'tydtemp1@gmail.com', '', 1, 2, '2019-10-23 10:07:49'),
(49, 41, 'temdasfafp1@gmail.com', '', 1, 2, '2019-10-23 10:25:32'),
(50, 42, 'temp111@gmail.com', '', 1, 2, '2019-10-23 10:26:20'),
(51, 43, 'temp111111@gmail.com', '', 1, 2, '2019-10-23 10:26:32'),
(52, 44, 'temp01@gmail.com', '', 1, 2, '2019-10-23 10:28:37'),
(53, 45, 'temp100@gmail.com', '', 1, 2, '2019-10-23 10:28:37'),
(54, 46, 'temp05451@gmail.com', '', 1, 2, '2019-10-23 10:38:05'),
(55, 47, 'temp054fsdf51@gmail', '', 1, 2, '2019-10-23 10:40:12'),
(56, 48, 'temp0srt54fsdf51', '', 1, 2, '2019-10-23 10:41:37'),
(57, 49, 'temp12345', '', 1, 2, '2019-10-23 10:42:24'),
(58, 50, 'test11@gmail.com', '', 1, 2, '2019-10-23 10:53:05'),
(59, 51, 'test13@gmail.com', '', 1, 2, '2019-10-23 10:53:06'),
(60, 52, 'test33@gmail.com', '', 1, 2, '2019-10-23 11:05:36'),
(61, 11, 'test55@gmail.com', '', 2, 2, '2019-10-23 11:05:59'),
(62, 12, 'test51@gmail.com', '', 2, 2, '2019-10-23 11:06:00'),
(63, 13, '', '', 2, 2, '2019-10-23 11:06:00'),
(64, 14, 'test52@gmail.com', '', 2, 2, '2019-10-23 11:06:00'),
(65, 53, 'test66@gmail.com', '', 1, 2, '2019-10-23 11:10:34'),
(66, 54, 'test67@gmail.com', '', 1, 2, '2019-10-23 11:10:35'),
(67, 55, 'test68@gmail.com', '', 1, 2, '2019-10-23 11:10:35'),
(68, 15, 'newwwwvendor@vendor.com', '', 2, 2, '2019-10-23 12:54:15'),
(69, 16, 'newwvendor@vendor.com', '', 2, 2, '2019-10-23 12:54:36'),
(70, 17, 'test@gmail.com', '', 2, 2, '2019-10-24 10:14:34'),
(71, 56, 'temp0@gmail.com', '', 1, 2, '2019-10-24 11:28:48'),
(72, 57, 'temp10@gmail.com', '', 1, 2, '2019-10-24 11:28:49'),
(73, 58, 'temptest@gmail.com', '', 1, 2, '2019-10-24 11:28:49'),
(74, 18, 'temp1@gmail.com', '', 2, 2, '2019-10-24 11:28:49'),
(75, 19, 'temp0@gmail.com', '', 2, 2, '2019-10-24 11:28:49'),
(76, 20, 'temptest@gmail.com', '', 2, 2, '2019-10-24 11:28:50'),
(77, 59, 'tempt@gmail.com', '', 1, 2, '2019-10-24 14:12:46'),
(78, 60, 'temptt@gmail.com', '', 1, 2, '2019-10-24 14:14:57'),
(79, 61, 'tempteserwert@gmail.com', '', 1, 2, '2019-10-24 14:15:18'),
(80, 62, 'temptrwert@gmail.com', '', 1, 2, '2019-10-24 14:15:18'),
(81, 21, 'temfgdfp1@gmail.com', '', 2, 2, '2019-10-24 14:15:51'),
(82, 22, 'temptsdfsest@gmail.com', '', 2, 2, '2019-10-24 14:15:51'),
(83, 63, 'magnil@gmail.com', '', 1, 4, '2019-10-25 05:46:54'),
(84, 64, 'admin@billflash.com', '', 1, 1, '2019-10-25 07:47:27'),
(85, 65, 'googfl@google', '', 1, 0, '2019-10-25 07:49:29'),
(86, 66, '123', '', 1, 0, '2019-10-25 07:51:34'),
(87, 67, 'google@google.com', '', 1, 1, '2019-10-25 07:59:43'),
(88, 68, 'gokul@vendor.com', '', 1, 0, '2019-11-01 10:36:13'),
(89, 69, 'google@vendor.com', '', 1, 0, '2019-11-01 12:15:33'),
(90, 23, 'goog@goog.com', '', 2, 2, '2019-11-01 12:17:21'),
(91, 70, 'testing@vendor.com', '', 1, 0, '2019-11-01 12:23:08'),
(92, 24, 'testings@vendor.com', '', 2, 2, '2019-11-01 12:26:39');

-- --------------------------------------------------------

--
-- Table structure for table `user_wish_list`
--

CREATE TABLE `user_wish_list` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `wish_user_id` int(11) DEFAULT NULL,
  `wish_user_type` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `vendor`
--

CREATE TABLE `vendor` (
  `vendor_id` int(11) NOT NULL,
  `user_type` int(11) DEFAULT NULL,
  `legal_name` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `contact_person` varchar(255) DEFAULT NULL,
  `contact_person_email` varchar(255) NOT NULL,
  `contact_person_phone` varchar(255) NOT NULL,
  `rating` float DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `expertise_category` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `country_code` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `admin_email` varchar(255) NOT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `fax_number` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `logo_name` varchar(255) DEFAULT NULL,
  `search_terms` varchar(255) DEFAULT NULL,
  `established_date` date DEFAULT NULL,
  `no_of_employee` int(11) DEFAULT NULL,
  `annual_revenue` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_date` varchar(255) DEFAULT NULL,
  `employees_count` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vendor`
--

INSERT INTO `vendor` (`vendor_id`, `user_type`, `legal_name`, `company_name`, `contact_person`, `contact_person_email`, `contact_person_phone`, `rating`, `address`, `city`, `province`, `postal_code`, `expertise_category`, `created_at`, `country_code`, `email`, `admin_email`, `phone_number`, `fax_number`, `website`, `logo_name`, `search_terms`, `established_date`, `no_of_employee`, `annual_revenue`, `description`, `created_date`, `employees_count`) VALUES
(1, 2, 'Vendor', 'Vendor Info tech', 'Asian Paint', '', '', 5, 'velachery', 'chennai', 'india', '600-042', 'Painting, Interior', '2019-10-17 14:40:12', 1, 'vendor@vendor.com', 'user@vendor.com', '12234354', 'fx2323244', 'vendor.com', 'Vendor Info', 'painting, interior', '2019-10-10', 100, '100000', 'This is an interior company', NULL, NULL),
(3, 2, 'legaltest2', 'Tester Company1', 'Test Team1', 'contact@vendor.com', '342342342342', NULL, 'velachery1', 'chennai1', 'india', 'pc323', 'Interior', '2019-10-24 12:05:55', 91, 'testvendor@tester.com', 'test@vendor.com', '566565', 'fx44444', '', 'Test2', '1223244', '2019-02-02', 200, '200000', 'Test company for testing purpose', NULL, NULL),
(4, 2, 'Asian', '', '', '', '', NULL, '', '', '', '', 'Paint', '2019-10-21 14:00:29', 0, 'admin@vendornew.com', 'newvendor@vendor.com', 'string', '', '', '', '', '2019-10-10', 10, '12000', '', NULL, NULL),
(5, 2, 'tester', 'Tester Company', 'Test Team', '', '', NULL, 'velachery', 'chennai', 'India', '12121', 'Testing', '2019-10-22 07:41:58', 1, 'tempvendor@tester.com', 'temp10@gmail.com', '12232343', 'fx23232', 'test.vendor.com', 'TestCompany', 'test', '2019-01-01', 100, '100000', 'Test company for testing purpose', NULL, NULL),
(6, 2, 'tester', 'Tester Company', 'Test Team', '', '', NULL, 'velachery', 'chennai', 'India', '12121', 'Testing', '2019-10-24 10:30:42', 1, 'testvendor33@tester.com', 'test55@gmail.com', '12232343', 'fx23232', 'test.vendor.com', 'TestCompany', 'test', '2019-01-01', 100, '100000', 'Test company for testing purpose', NULL, NULL),
(7, 2, 'tester', 'Tester Company', 'Test Team', '', '', NULL, 'velachery', 'chennai', 'India', '12121', 'Testing', '2019-10-24 10:30:43', 1, '', 'test@vendor.com', '12232343', 'fx23232', 'test.vendor.com', 'TestCompany', 'test', '2019-01-01', 100, '100000', 'Test company for testing purpose', NULL, NULL),
(8, 2, 'tester', 'Tester Company', 'Test Team', '', '', NULL, 'velachery', 'chennai', 'India', '12121', 'Testing', '2019-10-24 10:32:36', 1, 'testvendor55@tester.com', 'test55@gmail.com', '12232343', 'fx23232', 'test.vendor.com', 'TestCompany', 'test', '2019-01-01', 100, '100000', 'Test company for testing purpose', NULL, NULL),
(9, 2, 'tester', 'Tester Company', 'Test Team', '', '', NULL, 'velachery', 'chennai', 'India', '12121', 'Testing', '2019-10-24 10:35:03', 1, 'testvendor88@tester.com', 'test55@gmail.com', '12232343', 'fx23232', 'test.vendor.com', 'TestCompany', 'test', '2019-01-01', 100, '100000', 'Test company for testing purpose', NULL, NULL),
(10, 2, 'tester', 'Tester Company', 'Test Team', '', '', NULL, 'velachery', 'chennai', 'India', '12121', 'Testing', '2019-10-24 10:39:07', 1, 'testvendor34@tester.com', 'test55@gmail.com', '12232343', 'fx23232', 'test.vendor.com', 'TestCompany', 'test', '2019-01-01', 100, '100000', 'Test company for testing purpose', NULL, NULL),
(11, 2, 'tester', 'Tester Company', 'Test Team', '', '', NULL, 'velachery', 'chennai', 'India', '12121', 'Testing', '2019-10-24 10:39:08', 1, 'testvendor341@tester.com', '', '12232343', 'fx23232', 'test.vendor.com', 'TestCompany', 'test', '2019-01-01', 100, '100000', 'Test company for testing purpose', NULL, NULL),
(12, 2, 'tester', 'Tester Company', 'Test Team', '', '', NULL, '', 'chennai', 'India', '12121', 'Testing', '2019-10-24 10:39:08', 1, 'testvendor342@tester.com', 'test55@vendor.com', '12232343', 'fx23232', 'test.vendor.com', 'TestCompany', 'test', '2019-01-01', 100, '100000', 'Test company for testing purpose', NULL, NULL),
(13, 2, 'tester', 'Tester Company', 'Test Team', '', '', NULL, 'velachery', '', 'India', '12121', 'Testing', '2019-10-24 10:39:08', 1, 'testvendor344@tester.com', 'test55@vendor.com', '12232343', 'fx23232', 'test.vendor.com', 'TestCompany', 'test', '2019-01-01', 100, '100000', 'Test company for testing purpose', NULL, NULL),
(14, 2, 'tester', '', 'Test Team', '', '', NULL, 'velachery', 'chennai', 'India', '12121', 'Testing', '2019-10-24 10:39:09', 1, 'testvendor345@tester.com', 'test55@vendor.com', '12232343', 'fx23232', 'test.vendor.com', 'TestCompany', 'test', '2019-01-01', 100, '100000', 'Test company for testing purpose', NULL, NULL),
(15, 2, 'tester', 'Tester Company', '', '', '', NULL, 'velachery', 'chennai', 'India', '12121', 'Testing', '2019-10-24 10:39:09', 1, 'testvendor346@tester.com', 'test55@vendor.com', '12232343', 'fx23232', 'test.vendor.com', 'TestCompany', 'test', '2019-01-01', 100, '100000', 'Test company for testing purpose', NULL, NULL),
(16, 2, 'tester', 'Tester Company', 'Test Team', '', '', NULL, 'velachery', 'chennai', 'India', '12121', 'Testing', '2019-10-24 10:39:09', 0, 'testvendor347@tester.com', 'test55@vendor.com', '12232343', 'fx23232', 'test.vendor.com', 'TestCompany', 'test', '2019-01-01', 100, '100000', 'Test company for testing purpose', NULL, NULL),
(17, 2, 'tester', 'Tester Company', 'Test Team', '', '', NULL, 'velachery', 'chennai', 'India', '12121', 'Testing', '2019-10-24 10:39:09', 1, 'testvendor348@tester.com', 'test55@vendor.com', '12232343', 'fx23232', 'test.vendor.com', 'TestCompany', 'test', '2019-01-01', 100, '100000', '', NULL, NULL),
(18, 2, 'tester', 'Tester Company', 'Test Team', '', '', NULL, 'velachery', 'chennai', 'India', '12121', 'Testing', '2019-10-24 10:39:09', 1, 'testvendor349@tester.com', 'test55@vendor.com', '12232343', 'fx23232', 'test.vendor.com', 'TestCompany', 'test', '2019-01-01', 0, '100000', 'Test company for testing purpose', NULL, NULL),
(19, 2, 'tester', 'Tester Company', 'Test Team', '', '', NULL, 'velachery', 'chennai', 'India', '12121', '', '2019-10-24 10:39:09', 1, 'testvendor3411@tester.com', 'test55@vendor.com', '12232343', 'fx23232', 'test.vendor.com', 'TestCompany', 'test', '2019-01-01', 100, '100000', 'Test company for testing purpose', NULL, NULL),
(20, 2, 'tester', 'Tester Company', 'Test Team', '', '', NULL, 'velachery', 'chennai', 'India', '12121', 'Testing', '2019-10-24 10:39:10', 1, 'testvendor3412@tester.com', 'test55@vendor.com', '12232343', '', 'test.vendor.com', 'TestCompany', 'test', '2019-01-01', 100, '100000', 'Test company for testing purpose', NULL, NULL),
(21, 2, '', 'Tester Company', 'Test Team', '', '', NULL, 'velachery', 'chennai', 'India', '12121', 'Testing', '2019-10-24 10:39:10', 1, 'testvendor3413@tester.com', 'test55@vendor.com', '12232343', 'fx23232', 'test.vendor.com', 'TestCompany', 'test', '2019-01-01', 100, '100000', 'Test company for testing purpose', NULL, NULL),
(22, 2, 'tester', 'Tester Company', 'Test Team', '', '', NULL, 'velachery', 'chennai', 'India', '12121', 'Testing', '2019-10-24 10:39:10', 1, 'testvendor3414@tester.com', 'test55@vendor.com', '12232343', 'fx23232', 'test.vendor.com', '', 'test', '2019-01-01', 100, '100000', 'Test company for testing purpose', NULL, NULL),
(23, 2, 'tester', 'Tester Company', 'Test Team', '', '', NULL, 'velachery', 'chennai', 'India', '12121', 'Testing', '2019-10-24 10:39:10', 1, 'testvendor3415@tester.com', 'test55@vendor.com', '', 'fx23232', 'test.vendor.com', 'TestCompany', 'test', '2019-01-01', 100, '100000', 'Test company for testing purpose', NULL, NULL),
(24, 2, 'tester', 'Tester Company', 'Test Team', '', '', NULL, 'velachery', 'chennai', 'India', '', 'Testing', '2019-10-24 10:39:10', 1, 'testvendor3416@tester.com', 'test55@vendor.com', '12232343', 'fx23232', 'test.vendor.com', 'TestCompany', 'test', '2019-01-01', 100, '100000', 'Test company for testing purpose', NULL, NULL),
(25, 2, 'tester', 'Tester Company', 'Test Team', '', '', NULL, 'velachery', 'chennai', '', '12121', 'Testing', '2019-10-24 10:39:10', 1, 'testvendor3417@tester.com', 'test55@vendor.com', '12232343', 'fx23232', 'test.vendor.com', 'TestCompany', 'test', '2019-01-01', 100, '100000', 'Test company for testing purpose', NULL, NULL),
(26, 2, 'tester', 'Tester Company', 'Test Team', '', '', NULL, 'velachery', 'chennai', 'India', '12121', 'Testing', '2019-10-24 10:39:10', 1, 'testvendor3418@tester.com', 'test55@vendor.com', '12232343', 'fx23232', 'test.vendor.com', 'TestCompany', 'test', '2019-01-01', 100, '100000', 'Test company for testing purpose', NULL, NULL),
(27, 2, 'tester', 'Tester Company', 'Test Team', '', '', NULL, 'velachery', 'chennai', 'India', '12121', 'Testing', '2019-10-24 10:39:10', 1, 'testvendor3419@tester.com', 'test55@vendor.com', '12232343', 'fx23232', 'test.vendor.com', 'TestCompany', '', '2019-01-01', 100, '100000', 'Test company for testing purpose', NULL, NULL),
(28, 2, 'tester', 'Tester Company', 'Test Team', '', '', NULL, 'velachery', 'chennai', 'India', '12121', 'Testing', '2019-10-24 10:39:11', 1, 'testvendor3420@tester.com', 'test55@vendor.com', '12232343', 'fx23232', 'test.vendor.com', 'TestCompany', 'test', '2019-01-01', 100, '100000', 'Test company for testing purpose', NULL, NULL),
(29, 2, 'tester', 'Tester Company', 'Test Team', '', '', NULL, 'velachery', 'chennai', 'India', '12121', 'Testing', '2019-10-24 10:39:11', 1, 'testvendor3421@tester.com', 'test55@vendor.com', '12232343', 'fx23232', '', 'TestCompany', 'test', '2019-01-01', 100, '100000', 'Test company for testing purpose', NULL, NULL),
(30, 2, '456', 'sdf', '', '', '', NULL, '456', '456', '456', '456', '', '2019-11-01 13:27:20', 0, 'admin@billflash.com', 'goog@goog.com', '456', '', '', '', '', '2019-11-27', 0, '', '456', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `vendor_notification_settings`
--

CREATE TABLE `vendor_notification_settings` (
  `client_notification_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `alert_marketplace_potential` int(11) DEFAULT NULL,
  `alert_new_review` int(11) DEFAULT NULL,
  `alert_new_messages` int(11) DEFAULT NULL,
  `alert_account_changes` int(11) DEFAULT NULL,
  `alert_project_changes` int(11) DEFAULT NULL,
  `alert_expiring_potentials` int(11) DEFAULT NULL,
  `alert_que_ans` int(11) DEFAULT NULL,
  `alert_bids_on_potentials` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `modified_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `vendor_user`
--

CREATE TABLE `vendor_user` (
  `user_id` int(11) NOT NULL,
  `user_type` int(11) DEFAULT NULL,
  `vendor_id` int(11) DEFAULT NULL,
  `user_role` int(11) DEFAULT NULL,
  `legal_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `created_date` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vendor_user`
--

INSERT INTO `vendor_user` (`user_id`, `user_type`, `vendor_id`, `user_role`, `legal_name`, `email`, `status`, `created_at`, `created_date`, `password`, `username`) VALUES
(1, 2, 1, 1, 'Vendor User', 'user@vendor.com', 20, '2019-10-17 15:18:27', NULL, NULL, NULL),
(2, 2, 1, 1, '', 'string', 20, '2019-10-17 15:18:31', NULL, NULL, NULL),
(3, 2, 7, 2, 'Tester', 'test@vendor.com', 10, '2019-10-24 10:30:43', NULL, NULL, NULL),
(4, 2, 3, 1, '', 'test1@vendor.com', 20, '2019-10-24 11:08:57', NULL, NULL, NULL),
(5, 2, 4, 2, '', 'newvendor@vendor.com', 20, '2019-10-21 13:59:13', NULL, NULL, NULL),
(6, 2, 1, 2, '', 'vendor10@gmail.com', 20, '2019-10-23 12:00:55', NULL, NULL, NULL),
(7, 2, 5, 2, '', 'temp10@gmail.com', 10, '2019-10-22 07:41:58', NULL, NULL, NULL),
(8, 2, 0, 2, '', 'newvendor1@vendor.com', 20, '2019-10-24 11:08:56', NULL, NULL, NULL),
(9, 2, 0, 2, '', 'newvebkmkndor@vendor.com', 20, '2019-10-24 11:08:56', NULL, NULL, NULL),
(10, 2, 0, 2, '', 'newvebetkmkndor@vendor.com', 20, '2019-10-24 11:08:56', NULL, NULL, NULL),
(11, 2, 10, 2, '', 'test55@gmail.com', 10, '2019-10-24 10:39:07', NULL, NULL, NULL),
(12, 0, 0, 2, '', 'test51@gmail.com', 20, '2019-10-24 11:08:56', NULL, NULL, NULL),
(13, 2, 11, 2, '', '', 10, '2019-10-24 10:39:08', NULL, NULL, NULL),
(14, 7, 0, 2, '', 'test52@gmail.com', 20, '2019-10-24 11:08:56', NULL, NULL, NULL),
(15, 1, 0, 2, '', 'newwwwvendor@vendor.com', 20, '2019-10-24 11:08:56', NULL, NULL, NULL),
(16, 2, 0, 2, '', 'newwvendor@vendor.com', 20, '2019-10-24 11:08:56', NULL, NULL, NULL),
(17, 2, 0, 2, '', 'test@gmail.com', 20, '2019-10-24 11:08:56', NULL, NULL, NULL),
(18, 1, 0, 2, '', 'temp1@gmail.com', 20, '2019-10-24 13:36:44', NULL, NULL, NULL),
(19, 0, 0, 2, '', 'temp0@gmail.com', 20, '2019-10-24 13:36:44', NULL, NULL, NULL),
(20, 1, 0, 2, '', 'temptest@gmail.com', 20, '2019-10-24 13:36:44', NULL, NULL, NULL),
(21, 2, 0, 2, '', 'temfgdfp1@gmail.com', 10, '2019-10-24 14:15:51', NULL, NULL, NULL),
(22, 2, 0, 2, '', 'temptsdfsest@gmail.com', 10, '2019-10-24 14:15:51', NULL, NULL, NULL),
(23, 2, 30, 2, '', 'goog@goog.com', 10, '2019-11-01 13:27:20', NULL, NULL, NULL),
(24, 2, 0, 2, '', 'testings@vendor.com', 10, '2019-11-01 12:26:39', NULL, NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `approved_tags`
--
ALTER TABLE `approved_tags`
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
  ADD PRIMARY KEY (`bid_id`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`client_id`);

--
-- Indexes for table `client_association`
--
ALTER TABLE `client_association`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `client_notification_settings`
--
ALTER TABLE `client_notification_settings`
  ADD PRIMARY KEY (`client_notification_id`);

--
-- Indexes for table `client_repository`
--
ALTER TABLE `client_repository`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `client_tasks`
--
ALTER TABLE `client_tasks`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `client_task_files`
--
ALTER TABLE `client_task_files`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `company_documents_verification`
--
ALTER TABLE `company_documents_verification`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `contract_insurance`
--
ALTER TABLE `contract_insurance`
  ADD PRIMARY KEY (`insurance_id`);

--
-- Indexes for table `contract_types`
--
ALTER TABLE `contract_types`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `country`
--
ALTER TABLE `country`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `group_settings`
--
ALTER TABLE `group_settings`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `insurance`
--
ALTER TABLE `insurance`
  ADD PRIMARY KEY (`insurance_id`);

--
-- Indexes for table `message_board`
--
ALTER TABLE `message_board`
  ADD PRIMARY KEY (`message_board_id`);

--
-- Indexes for table `message_board_comments`
--
ALTER TABLE `message_board_comments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `message_board_type`
--
ALTER TABLE `message_board_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `organisation`
--
ALTER TABLE `organisation`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `organisation_amenities_info`
--
ALTER TABLE `organisation_amenities_info`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `organisation_billing_address`
--
ALTER TABLE `organisation_billing_address`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `organisation_payment_card_details`
--
ALTER TABLE `organisation_payment_card_details`
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
-- Indexes for table `project_awards`
--
ALTER TABLE `project_awards`
  ADD PRIMARY KEY (`project_awards_id`);

--
-- Indexes for table `project_bidding_files`
--
ALTER TABLE `project_bidding_files`
  ADD PRIMARY KEY (`bidding_file_id`);

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
-- Indexes for table `project_tags`
--
ALTER TABLE `project_tags`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ratings`
--
ALTER TABLE `ratings`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `rating_modules`
--
ALTER TABLE `rating_modules`
  ADD PRIMARY KEY (`rating_module_id`);

--
-- Indexes for table `reviews`
--
ALTER TABLE `reviews`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `serviced_areas`
--
ALTER TABLE `serviced_areas`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `subscription`
--
ALTER TABLE `subscription`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `subscription_types`
--
ALTER TABLE `subscription_types`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `support_user`
--
ALTER TABLE `support_user`
  ADD PRIMARY KEY (`support_user_id`);

--
-- Indexes for table `user_account`
--
ALTER TABLE `user_account`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_wish_list`
--
ALTER TABLE `user_wish_list`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `vendor`
--
ALTER TABLE `vendor`
  ADD PRIMARY KEY (`vendor_id`);

--
-- Indexes for table `vendor_notification_settings`
--
ALTER TABLE `vendor_notification_settings`
  ADD PRIMARY KEY (`client_notification_id`);

--
-- Indexes for table `vendor_user`
--
ALTER TABLE `vendor_user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `approved_tags`
--
ALTER TABLE `approved_tags`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `bidding_products`
--
ALTER TABLE `bidding_products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `bids`
--
ALTER TABLE `bids`
  MODIFY `bid_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `client_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;

--
-- AUTO_INCREMENT for table `client_association`
--
ALTER TABLE `client_association`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- AUTO_INCREMENT for table `client_notification_settings`
--
ALTER TABLE `client_notification_settings`
  MODIFY `client_notification_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `client_repository`
--
ALTER TABLE `client_repository`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `client_tasks`
--
ALTER TABLE `client_tasks`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `client_task_files`
--
ALTER TABLE `client_task_files`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `company_documents_verification`
--
ALTER TABLE `company_documents_verification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `contract_insurance`
--
ALTER TABLE `contract_insurance`
  MODIFY `insurance_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `contract_types`
--
ALTER TABLE `contract_types`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `country`
--
ALTER TABLE `country`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `group_settings`
--
ALTER TABLE `group_settings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `insurance`
--
ALTER TABLE `insurance`
  MODIFY `insurance_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `message_board`
--
ALTER TABLE `message_board`
  MODIFY `message_board_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `message_board_comments`
--
ALTER TABLE `message_board_comments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `message_board_type`
--
ALTER TABLE `message_board_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `organisation`
--
ALTER TABLE `organisation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=123;

--
-- AUTO_INCREMENT for table `organisation_amenities_info`
--
ALTER TABLE `organisation_amenities_info`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `organisation_billing_address`
--
ALTER TABLE `organisation_billing_address`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `organisation_payment_card_details`
--
ALTER TABLE `organisation_payment_card_details`
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
  MODIFY `project_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `project_awards`
--
ALTER TABLE `project_awards`
  MODIFY `project_awards_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `project_bidding_files`
--
ALTER TABLE `project_bidding_files`
  MODIFY `bidding_file_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `project_files`
--
ALTER TABLE `project_files`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `project_products`
--
ALTER TABLE `project_products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `project_questions`
--
ALTER TABLE `project_questions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `project_tags`
--
ALTER TABLE `project_tags`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ratings`
--
ALTER TABLE `ratings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `rating_modules`
--
ALTER TABLE `rating_modules`
  MODIFY `rating_module_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `reviews`
--
ALTER TABLE `reviews`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `serviced_areas`
--
ALTER TABLE `serviced_areas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `subscription`
--
ALTER TABLE `subscription`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `subscription_types`
--
ALTER TABLE `subscription_types`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `support_user`
--
ALTER TABLE `support_user`
  MODIFY `support_user_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user_account`
--
ALTER TABLE `user_account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=93;

--
-- AUTO_INCREMENT for table `user_wish_list`
--
ALTER TABLE `user_wish_list`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `vendor`
--
ALTER TABLE `vendor`
  MODIFY `vendor_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `vendor_notification_settings`
--
ALTER TABLE `vendor_notification_settings`
  MODIFY `client_notification_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `vendor_user`
--
ALTER TABLE `vendor_user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
--
-- Database: `springbootdb`
--
CREATE DATABASE IF NOT EXISTS `springbootdb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `springbootdb`;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `email`) VALUES
(1, 'Prakash', 'Prakash@Explorer.com'),
(2, 'Prakash', 'Prakash@Explorer.com'),
(3, 'Prakash', 'Prakash@Explorer.com'),
(4, 'Prakash', 'Prakash@Explorer.com'),
(5, 'akash', 'akash@Explorer.com'),
(6, 'Prakash', 'Prakash@Explorer.com'),
(7, 'Prakash', 'Prakash@Explorer.com'),
(8, 'Prakash', 'Prakash@Explorer.com'),
(9, 'Prakash', 'Prakash@Explorer.com'),
(10, 'Prakash', 'Prakash@Explorer.com'),
(11, 'Prakash', 'Prakash@Explorer.com'),
(12, 'Prakash', 'Prakash@Explorer.com'),
(13, 'Prakash', 'Prakash@Explorer.com'),
(14, 'akash', 'akash@Explorer.com'),
(15, 'akash', 'akash@Explorer.com'),
(17, 'jhkhj', 'jhjhhjjh');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- Database: `spring_boot_test`
--
CREATE DATABASE IF NOT EXISTS `spring_boot_test` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `spring_boot_test`;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL,
  `name` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `email` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `address` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `phone` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`admin_id`, `name`, `email`, `password`, `address`, `phone`) VALUES
(1, 'Administrator', 'admin@mayaclinic.com', 'maya123', 'Admin Address', '9800000000');

-- --------------------------------------------------------

--
-- Table structure for table `appointment`
--

CREATE TABLE `appointment` (
  `appointment_id` int(11) NOT NULL,
  `specialization_id` int(11) NOT NULL DEFAULT 0,
  `appt_unique_id` varchar(33) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL,
  `appointment_date` varchar(10) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `appointment_amount` int(11) NOT NULL DEFAULT 0,
  `appointment_currency` varchar(11) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `appointment_call_length` varchar(22) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `appointment_start_time` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `appointment_end_time` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `appointment_initiate_time_doctor` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `appointment_end_time_doctor` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `appointment_initiate_time_patient` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `appointment_end_time_patient` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `appointment_call_duration` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `appointment_purpose` varchar(2000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `appointment_attachment` varchar(2000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `appointment_status` enum('0','1','2','3','4','5','6','7','8','9','10') CHARACTER SET utf8 NOT NULL DEFAULT '0',
  `appointment_status_patient` enum('0','1','2','3','4','5') CHARACTER SET utf8 NOT NULL DEFAULT '0',
  `appointment_canceled_reason_doctor` varchar(2000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `appointment_canceled_reason_patient` varchar(2000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `appointment_canceled_date_expired` varchar(250) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `appointment_call_id_doctor` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `appointment_call_time_exceeded` varchar(110) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `appointment_call_note_doctor` varchar(2000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `appointment_call_id_patient` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `appointment_call_note_patient` varchar(2000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `appointment_ratings` int(11) NOT NULL DEFAULT 0,
  `appointment_doctor_subject` varchar(2000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `appointment_doctor_objective` varchar(2000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `appointment_doctor_action` varchar(2000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `appointment_doctor_plan` varchar(2000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `transaction_id` int(11) NOT NULL DEFAULT 0,
  `maya_transaction_id` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `transaction_status` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `appointment_payment_type` enum('0','1','2','3','4','5') CHARACTER SET utf8 NOT NULL DEFAULT '0',
  `created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `appointment`
--

INSERT INTO `appointment` (`appointment_id`, `specialization_id`, `appt_unique_id`, `doctor_id`, `patient_id`, `appointment_date`, `appointment_amount`, `appointment_currency`, `appointment_call_length`, `appointment_start_time`, `appointment_end_time`, `appointment_initiate_time_doctor`, `appointment_end_time_doctor`, `appointment_initiate_time_patient`, `appointment_end_time_patient`, `appointment_call_duration`, `appointment_purpose`, `appointment_attachment`, `appointment_status`, `appointment_status_patient`, `appointment_canceled_reason_doctor`, `appointment_canceled_reason_patient`, `appointment_canceled_date_expired`, `appointment_call_id_doctor`, `appointment_call_time_exceeded`, `appointment_call_note_doctor`, `appointment_call_id_patient`, `appointment_call_note_patient`, `appointment_ratings`, `appointment_doctor_subject`, `appointment_doctor_objective`, `appointment_doctor_action`, `appointment_doctor_plan`, `transaction_id`, `maya_transaction_id`, `transaction_status`, `appointment_payment_type`, `created_date`, `updated_date`) VALUES
(1, 0, 'APP00005', 1, 1, '2019-05-03', 100, 'SAR', '23', '04:30 PM', '', '', '02:20 PM', '', '', '', '', '', '0', '0', '', '', '', '0', '', '', '', '', 5, '', '', '', '', 0, '', '', '0', '2019-01-08 11:52:35', '2019-05-03 10:45:09'),
(2, 0, '', 1, 1, '2019-02-27', 100, 'SAR', '40', '01:00 PM', '', '', '', '', '', '', '', '', '1', '0', '', '', '', '', '', '', '', '', 5, '', '', '', '', 0, '', '', '0', '2019-01-08 11:52:35', '2019-05-30 14:10:24'),
(3, 0, '', 1, 1, '2019-01-09', 100, 'SAR', '15', '09:30 PM', '', '', '', '', '', '', '', '', '2', '0', '', '', '', '', '', '', '', '', 4, '', '', '', '', 0, '', '', '0', '2019-01-08 11:52:35', '2019-05-30 14:10:27'),
(4, 0, '', 1, 1, '2019-01-14', 100, 'SAR', '10', '2:18 PM', '', '', '', '', '', '', 'NA', '', '3', '0', '', '', '', '', '', '', '', '', 0, '', '', '', '', 2, '', 'Success', '3', '2019-01-11 13:53:17', '2019-05-30 14:10:31'),
(5, 0, '', 1, 1, '2019-01-14', 100, 'SAR', '10', '2:18 PM', '', '', '', '', '', '', 'NA', '', '4', '0', '', '', '', '', '', '', '', '', 0, '', '', '', '', 3, '', 'Success', '3', '2019-01-11 13:56:02', '2019-05-30 14:10:34'),
(6, 0, '', 1, 1, '2019-01-14', 100, 'SAR', '10', '2:18 PM', '', '', '', '', '', '', 'NA', '', '5', '0', '', '', '', '', '', '', '', '', 0, '', '', '', '', 3, '', 'Success', '3', '2019-01-11 13:56:02', '2019-05-30 14:10:39'),
(7, 0, '', 1, 1, '2019-06-01', 100, 'SAR', '10', '2:18 PM', '', '', '', '', '', '', 'NA', '', '7', '0', '', '', '', '', '', '', '', '', 0, '', '', '', '', 3, '', 'Success', '3', '2019-06-01 13:56:02', '2019-06-01 13:19:44'),
(8, 0, '', 1, 1, '2019-01-14', 100, 'SAR', '10', '2:18 PM', '', '', '', '', '', '', 'NA', '', '6', '0', '', '', '', '', '', '', '', '', 0, '', '', '', '', 3, '', 'Success', '3', '2019-01-11 13:56:02', '2019-05-30 14:10:43');

-- --------------------------------------------------------

--
-- Table structure for table `appointment_call_status`
--

CREATE TABLE `appointment_call_status` (
  `appointment_call_status_id` int(11) NOT NULL,
  `appointment_failed_reason` varchar(250) CHARACTER SET utf8 NOT NULL,
  `appointment_user_type` int(2) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `appointment_call_status`
--

INSERT INTO `appointment_call_status` (`appointment_call_status_id`, `appointment_failed_reason`, `appointment_user_type`, `created_date`) VALUES
(1, 'Patient Not Available', 1, '2017-07-29 12:36:06'),
(2, 'Patient Did\'t Pick The Call', 1, '2017-07-29 12:36:42'),
(3, 'Technical Issue', 1, '2017-07-29 12:36:31'),
(4, 'Doctor Not Available', 2, '2017-07-29 12:36:31'),
(5, 'Doctor Did\'t Pick The Call', 2, '2017-08-02 13:10:07'),
(6, 'Technical Issue', 2, '2017-08-02 13:10:07');

-- --------------------------------------------------------

--
-- Table structure for table `appointment_slot_block`
--

CREATE TABLE `appointment_slot_block` (
  `appointment_block_id` int(22) NOT NULL,
  `doctor_id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL,
  `appointment_date` varchar(22) NOT NULL,
  `appointment_time` varchar(11) NOT NULL,
  `block_initate_time` varchar(22) NOT NULL,
  `status` enum('1','2') NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `claim`
--

CREATE TABLE `claim` (
  `claim_id` int(11) NOT NULL,
  `claim_no` varchar(22) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `patient_id` int(11) NOT NULL DEFAULT 0,
  `appointment_id` int(11) NOT NULL DEFAULT 0,
  `appt_unique_id` varchar(33) CHARACTER SET utf8 NOT NULL,
  `claim_status` enum('0','1','2','3','4','5','6') NOT NULL DEFAULT '0',
  `claim_reason_id` int(11) NOT NULL DEFAULT 0,
  `claim_req_note` varchar(3000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `claim_reject_note` varchar(1000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `claim_resolved_by` varchar(555) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `claim_raised_date` datetime NOT NULL DEFAULT current_timestamp(),
  `claim_resolved_date` datetime NOT NULL DEFAULT current_timestamp(),
  `created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `claim`
--

INSERT INTO `claim` (`claim_id`, `claim_no`, `patient_id`, `appointment_id`, `appt_unique_id`, `claim_status`, `claim_reason_id`, `claim_req_note`, `claim_reject_note`, `claim_resolved_by`, `claim_raised_date`, `claim_resolved_date`, `created_date`, `updated_date`) VALUES
(1, 'CLM00001', 1, 3, 'APP0001', '2', 1, 'Need to refund my amount to my bank account', 'claim resolved-amount refund to your wallet', 'Ram', '2019-02-27 12:39:11', '2019-03-06 11:09:30', '2019-02-27 07:09:11', '2019-03-06 08:09:30'),
(2, 'CLM00002', 1, 1, 'APP0001', '1', 1, 'Need to refund my amount to my bank account', 'This Claim Resolved ', 'Ramalingam', '2019-02-27 12:39:11', '2019-02-27 12:39:11', '2019-02-27 07:09:11', '2019-03-06 07:54:11');

-- --------------------------------------------------------

--
-- Table structure for table `claim_configuration`
--

CREATE TABLE `claim_configuration` (
  `claim_config_id` int(11) NOT NULL,
  `raise_claim_duration_hrs` varchar(11) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `claim_configuration`
--

INSERT INTO `claim_configuration` (`claim_config_id`, `raise_claim_duration_hrs`, `created_date`, `updated_date`) VALUES
(1, '24', '2019-03-01 07:21:25', '2019-03-01 07:21:25');

-- --------------------------------------------------------

--
-- Table structure for table `claim_options`
--

CREATE TABLE `claim_options` (
  `claim_option_id` int(11) NOT NULL,
  `option_name` varchar(555) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `option_name_in_arabic` varchar(555) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `claim_options`
--

INSERT INTO `claim_options` (`claim_option_id`, `option_name`, `option_name_in_arabic`, `created_date`, `updated_date`) VALUES
(1, 'Video Call Failed to Connect ', 'This is Arabic', '2019-02-27 07:09:50', '2019-02-27 07:09:50');

-- --------------------------------------------------------

--
-- Table structure for table `dependent`
--

CREATE TABLE `dependent` (
  `dependent_id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL DEFAULT 0,
  `dependent_inc_id` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `dependent_nationality_id` varchar(33) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `dependent_first_name` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `dependent_last_name` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `dependent_dob` varchar(10) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `dependent_gender` varchar(11) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `dependent_prof_photo` varchar(155) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `dependent_type_id` int(5) NOT NULL DEFAULT 0,
  `dependent_status` enum('Active','InActive') COLLATE utf8_unicode_ci DEFAULT 'Active',
  `created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `dependent`
--

INSERT INTO `dependent` (`dependent_id`, `patient_id`, `dependent_inc_id`, `dependent_nationality_id`, `dependent_first_name`, `dependent_last_name`, `dependent_dob`, `dependent_gender`, `dependent_prof_photo`, `dependent_type_id`, `dependent_status`, `created_date`, `updated_date`) VALUES
(1, 1, 'MCD0001', '333', 'Jaihindh', 'Raja A.C', '10-02-2002', 'Male', '', 3, 'Active', '2019-04-09 13:46:00', '2019-04-09 13:52:37'),
(2, 1, 'MCD0002', '333', 'Prakash', 'M', '10-02-2002', 'Male', '', 3, 'Active', '2019-04-10 12:18:56', '2019-04-10 12:18:56'),
(7, 1, 'MCD0003', '21542154', 'Sree', 'M', '10-02-2002', 'Male', '', 3, 'Active', '2019-04-10 12:27:45', '2019-04-10 12:27:45'),
(8, 1, 'MCD0004', '21542154', 'Purusoth', 'M', '10-02-2002', 'Male', '', 3, 'Active', '2019-04-10 12:28:08', '2019-04-10 12:28:08'),
(9, 1, 'MCD0005', '21542154', 'Purusoth', 'M', '10-02-2002', 'Male', '', 3, 'Active', '2019-04-10 12:28:19', '2019-04-10 12:28:19');

-- --------------------------------------------------------

--
-- Table structure for table `dependent_max_count`
--

CREATE TABLE `dependent_max_count` (
  `dependent_max_id` int(11) NOT NULL,
  `dependent_max_count` int(11) NOT NULL DEFAULT 0,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `dependent_type`
--

CREATE TABLE `dependent_type` (
  `dependent_type_id` int(11) NOT NULL,
  `dependent_type` varchar(121) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `dependent_type_arabic` varchar(121) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `dependent_type`
--

INSERT INTO `dependent_type` (`dependent_type_id`, `dependent_type`, `dependent_type_arabic`, `created_date`, `updated_date`) VALUES
(1, 'Daughter', 'ابنة', '2019-04-08 12:39:59', '2019-04-09 05:45:34'),
(2, 'Daughterd', 'd', '2019-04-09 05:56:50', '2019-04-09 05:56:50'),
(3, 'Son', 'Son', '2019-04-09 05:57:52', '2019-04-09 06:02:20');

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `doctor_id` int(11) NOT NULL,
  `doctor_inc_id` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_first_name` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_last_name` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_arabic_name` varchar(155) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_email` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_password` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_mln_idno` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_mobile_country_code` varchar(10) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_mobile` varchar(22) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_dob` varchar(22) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_gender` varchar(11) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `specialization_id` varchar(55) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_year_of_experience` varchar(55) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `language_known_id` varchar(55) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_short_description` varchar(4000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_prof_photo` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_doc_images` varchar(2000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_hierarchy` varchar(111) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_hierarchy_arabic` varchar(111) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `zoom_email` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `zoom_meeting_id` varchar(55) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `zoom_user_id` varchar(110) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `zoom_token` varchar(2000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `reg_approve_status` tinyint(4) DEFAULT 0,
  `doctor_reject_reason` varchar(2000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_reject_note` varchar(2000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_otp_no` varchar(11) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_otp_status` enum('0','1','2') COLLATE utf8_unicode_ci DEFAULT '0',
  `doctor_status` enum('Active','InActive') COLLATE utf8_unicode_ci DEFAULT 'Active',
  `created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `sign_up_date_time` datetime NOT NULL DEFAULT current_timestamp(),
  `service_agreement_updated_on` datetime NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`doctor_id`, `doctor_inc_id`, `doctor_first_name`, `doctor_last_name`, `doctor_arabic_name`, `doctor_email`, `doctor_password`, `doctor_mln_idno`, `doctor_mobile_country_code`, `doctor_mobile`, `doctor_dob`, `doctor_gender`, `specialization_id`, `doctor_year_of_experience`, `language_known_id`, `doctor_short_description`, `doctor_prof_photo`, `doctor_doc_images`, `doctor_hierarchy`, `doctor_hierarchy_arabic`, `zoom_email`, `zoom_meeting_id`, `zoom_user_id`, `zoom_token`, `reg_approve_status`, `doctor_reject_reason`, `doctor_reject_note`, `doctor_otp_no`, `doctor_otp_status`, `doctor_status`, `created_date`, `updated_date`, `sign_up_date_time`, `service_agreement_updated_on`) VALUES
(1, 'ENT0001', 'Ram', '', 'للل', 'ram@gamil.com', 'd41d8cd98f00b204e9800998ecf8427e', '123456785', '+91', '9500051865', '1985-02-05', 'Male', '1', '8', '1,2', 'Best doctor', '95d0ff5aabb5914023aa03f6d529f89f.jpg', '', 'Sr Doctor Sr Doctor  Sr Doctor ', 'الأب الطبيب الأب الطبيب الأب الطبيب', 'jklsdfdgkl@dflkldfgl.comdf', '3288810903', '', '', 0, '', '', '737182', '2', 'Active', '2019-03-01 04:58:10', '2019-05-28 12:47:03', '2019-02-19 18:41:11', '0000-00-00 00:00:00'),
(209, 'ENT0002', 'Devss', '', 'للل', 'dev@gamil.com', 'd41d8cd98f00b204e9800998ecf8427e', '123456785', '+91', '9500051866', '1985-02-05', 'Male', '2', '8', '1,2', 'best doctor ', '', '', 'Sr Doctor in Army hospital', '', 'fsdgsdf@dfd.dfd', '', '', '', 1, '', '', '737182', '2', 'Active', '2019-02-01 04:58:10', '2019-03-15 10:01:43', '2019-02-19 18:41:11', '0000-00-00 00:00:00'),
(210, 'ENT0003', 'Devan', '', 'للل', 'dev1@gamil.com', 'd41d8cd98f00b204e9800998ecf8427e', '123456785', '+91', '9500051866', '1985-02-20', 'Male', '3', '8', '1,2', 'Best doctor', 'b6369d6a1ad4e617ecafc26dd17873f1.png', '', 'Dean', '', 'dev1@gmail.com', '', '', '', 1, '', '', '737182', '2', 'Active', '2019-03-20 04:58:10', '2019-05-22 12:18:57', '2019-02-19 18:41:11', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `doctor_bank_details`
--

CREATE TABLE `doctor_bank_details` (
  `doctor_bank_id` int(22) NOT NULL,
  `doctor_id` int(22) NOT NULL,
  `doctor_bank_name` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_acc_number` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_iban_number` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `doctor_bank_details`
--

INSERT INTO `doctor_bank_details` (`doctor_bank_id`, `doctor_id`, `doctor_bank_name`, `doctor_acc_number`, `doctor_iban_number`, `created_date`, `updated_date`) VALUES
(2, 1, '0', '0', '0', '2019-03-15 12:32:10', '2019-03-25 14:43:36');

-- --------------------------------------------------------

--
-- Table structure for table `doctor_clinic_details`
--

CREATE TABLE `doctor_clinic_details` (
  `doctor_clinic_id` int(22) NOT NULL,
  `doctor_id` int(22) NOT NULL,
  `doctor_clinic_name` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_clinic_address` varchar(4000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_location_map` varchar(4000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_clinic_address1` varchar(4000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_clinic_latitude` varchar(150) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_clinic_longitude` varchar(150) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_clinic_city` varchar(110) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_clinic_state` varchar(55) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_clinic_postal_code` varchar(22) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_clinic_country` varchar(55) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_clinic_photos` varchar(2000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `created_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `doctor_clinic_details`
--

INSERT INTO `doctor_clinic_details` (`doctor_clinic_id`, `doctor_id`, `doctor_clinic_name`, `doctor_clinic_address`, `doctor_location_map`, `doctor_clinic_address1`, `doctor_clinic_latitude`, `doctor_clinic_longitude`, `doctor_clinic_city`, `doctor_clinic_state`, `doctor_clinic_postal_code`, `doctor_clinic_country`, `doctor_clinic_photos`, `created_date`) VALUES
(1, 1, '0', '0', '0', '', '', '', 'Chennai', 'Tamil Nadu', '600044', 'India', '', '2019-03-25 14:43:37');

-- --------------------------------------------------------

--
-- Table structure for table `doctor_consultaion`
--

CREATE TABLE `doctor_consultaion` (
  `doctor_consultation_id` int(11) NOT NULL,
  `doctor_id` int(11) NOT NULL,
  `doctor_consultation_fee` varchar(11) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_consultation_currency` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_consultation_length` varchar(11) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_consultation_timezone` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `doctor_consultaion`
--

INSERT INTO `doctor_consultaion` (`doctor_consultation_id`, `doctor_id`, `doctor_consultation_fee`, `doctor_consultation_currency`, `doctor_consultation_length`, `doctor_consultation_timezone`, `created_date`, `updated_date`) VALUES
(1, 1, '300', 'SAR', '45', 'Asia/Calcutta', '2018-12-19 04:54:15', '2019-05-03 10:42:43');

-- --------------------------------------------------------

--
-- Table structure for table `doctor_consultaion_timing`
--

CREATE TABLE `doctor_consultaion_timing` (
  `doctor_consult_time_id` int(11) NOT NULL,
  `doctor_consultation_id` int(11) NOT NULL,
  `doctor_id` int(11) NOT NULL,
  `doctor_consult_day` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_consult_session1_startTime` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_consult_session1_endTime` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_consult_session2_startTime` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_consult_session2_endTime` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `doctor_consultaion_timing`
--

INSERT INTO `doctor_consultaion_timing` (`doctor_consult_time_id`, `doctor_consultation_id`, `doctor_id`, `doctor_consult_day`, `doctor_consult_session1_startTime`, `doctor_consult_session1_endTime`, `doctor_consult_session2_startTime`, `doctor_consult_session2_endTime`, `created_date`, `updated_date`) VALUES
(32, 1, 1, 'Monday', '09:00 AM', '12:00 PM', '09:41 PM', '06:45 PM', '2017-09-08 13:25:48', '2018-11-28 08:17:58'),
(33, 1, 1, 'Tuesday', '09:00 AM', '12:30 PM', '05:25 PM', '07:25 PM', '2018-03-23 13:56:21', '2018-11-28 08:17:58'),
(34, 1, 1, 'Wednesday', '03:00 PM', '04:00 PM', '07:00 PM', '11:00 PM', '2018-03-25 12:54:27', '2018-12-19 10:54:18'),
(35, 1, 1, 'Thursday', '07:00 AM', '07:15 PM', '', '', '2017-08-12 14:56:39', '2018-12-19 10:50:53'),
(36, 1, 1, 'Friday', '02:40 AM', '08:40 AM', '', '', '2017-09-01 13:37:49', '2018-11-28 08:17:58'),
(37, 1, 1, 'Saturday', '09:00 AM', '11:00 AM', '', '', '2017-12-29 04:16:49', '2018-11-28 08:17:58'),
(38, 1, 1, 'Sunday', '10:58 AM', '02:00 PM', '', '', '2017-12-29 01:58:37', '2018-11-28 08:17:58');

-- --------------------------------------------------------

--
-- Table structure for table `doctor_education`
--

CREATE TABLE `doctor_education` (
  `education_id` int(11) NOT NULL,
  `doctor_id` int(22) NOT NULL DEFAULT 0,
  `degree` varchar(33) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `description` varchar(2000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `start_year` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `end_year` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `doctor_education`
--

INSERT INTO `doctor_education` (`education_id`, `doctor_id`, `degree`, `description`, `start_year`, `end_year`, `created_date`, `updated_date`) VALUES
(1, 1, 'BE(CSE)', 'part time course', '2016', '2019', '2019-03-26 13:26:45', '2019-03-26 13:26:45');

-- --------------------------------------------------------

--
-- Table structure for table `doctor_experience`
--

CREATE TABLE `doctor_experience` (
  `experience_id` int(11) NOT NULL,
  `doctor_id` int(22) NOT NULL DEFAULT 0,
  `job_position` varchar(55) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `description` varchar(2000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `from_date` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `end_date` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `doctor_experience`
--

INSERT INTO `doctor_experience` (`experience_id`, `doctor_id`, `job_position`, `description`, `from_date`, `end_date`, `created_date`, `updated_date`) VALUES
(1, 1, 'Senior Doctor', 'have good experience in surgery', '2009', '2013', '2019-03-26 11:22:38', '2019-03-26 11:22:38');

-- --------------------------------------------------------

--
-- Table structure for table `doctor_payment`
--

CREATE TABLE `doctor_payment` (
  `doctor_payment_id` int(11) NOT NULL,
  `doctor_id` int(22) NOT NULL,
  `doctor_outstanding_amount` decimal(25,2) NOT NULL,
  `doctor_total_earnings` decimal(25,2) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `doctor_payment`
--

INSERT INTO `doctor_payment` (`doctor_payment_id`, `doctor_id`, `doctor_outstanding_amount`, `doctor_total_earnings`, `created_date`, `updated_date`) VALUES
(1, 210, '200.00', '901.00', '2019-05-22 12:23:01', '2019-05-22 12:57:05');

-- --------------------------------------------------------

--
-- Table structure for table `doctor_payment_transaction`
--

CREATE TABLE `doctor_payment_transaction` (
  `payment_transaction_id` int(11) NOT NULL,
  `doctor_id` int(22) NOT NULL,
  `transaction_amount` decimal(25,2) NOT NULL,
  `transaction_currency` varchar(10) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `transaction_status` enum('0','1','2','3','4','5') COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `transaction_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `admin_pay_amount` decimal(25,2) NOT NULL,
  `maya_commission_amount` decimal(25,2) NOT NULL,
  `maya_transaction_charge` decimal(25,2) NOT NULL,
  `maya_tax` decimal(25,2) NOT NULL,
  `transaction_reject_reason` varchar(2000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `doctor_payment_transaction`
--

INSERT INTO `doctor_payment_transaction` (`payment_transaction_id`, `doctor_id`, `transaction_amount`, `transaction_currency`, `transaction_status`, `transaction_date`, `admin_pay_amount`, `maya_commission_amount`, `maya_transaction_charge`, `maya_tax`, `transaction_reject_reason`, `created_date`, `updated_date`) VALUES
(1, 210, '390.00', 'SAR', '3', '2019-05-22 04:08:35', '271.00', '117.00', '2.00', '0.00', '', '2019-05-22 04:08:35', '2019-05-22 12:22:10'),
(2, 210, '1.00', 'SAR', '1', '2019-05-22 05:27:50', '0.00', '0.00', '0.00', '0.00', '', '2019-05-22 05:27:50', '2019-05-22 12:21:52'),
(3, 210, '200.00', 'SAR', '1', '2019-05-22 05:29:58', '0.00', '0.00', '0.00', '0.00', '', '2019-05-22 05:29:58', '2019-05-22 12:21:58'),
(4, 210, '201.00', 'SAR', '3', '2019-05-22 05:32:36', '138.70', '60.30', '2.00', '0.00', '', '2019-05-22 05:32:36', '2019-05-22 12:57:05'),
(5, 210, '200.00', 'SAR', '1', '2019-05-22 05:33:41', '0.00', '0.00', '0.00', '0.00', '', '2019-05-22 05:33:41', '2019-05-22 12:22:07');

-- --------------------------------------------------------

--
-- Table structure for table `fcm_device_token`
--

CREATE TABLE `fcm_device_token` (
  `fcmId` int(11) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `deviceToken` text DEFAULT NULL,
  `userType` int(5) NOT NULL,
  `deviceType` enum('0','1','2','3','4') NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fcm_device_token`
--

INSERT INTO `fcm_device_token` (`fcmId`, `userId`, `deviceToken`, `userType`, `deviceType`) VALUES
(1, 1, 'sdfsdfsdfsdf', 1, '1'),
(2, 1, '454545dsfsdfsdf', 2, '1');

-- --------------------------------------------------------

--
-- Table structure for table `follower`
--

CREATE TABLE `follower` (
  `follower_id` int(11) NOT NULL,
  `doctor_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `user_type` int(3) NOT NULL,
  `follow_status` int(11) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `language`
--

CREATE TABLE `language` (
  `phrase_id` int(11) NOT NULL,
  `phrase` longtext COLLATE utf8_unicode_ci NOT NULL,
  `english` longtext COLLATE utf8_unicode_ci NOT NULL,
  `bengali` longtext COLLATE utf8_unicode_ci NOT NULL,
  `spanish` longtext COLLATE utf8_unicode_ci NOT NULL,
  `arabic` longtext COLLATE utf8_unicode_ci NOT NULL,
  `dutch` longtext COLLATE utf8_unicode_ci NOT NULL,
  `russian` longtext COLLATE utf8_unicode_ci NOT NULL,
  `chinese` longtext COLLATE utf8_unicode_ci NOT NULL,
  `turkish` longtext COLLATE utf8_unicode_ci NOT NULL,
  `portuguese` longtext COLLATE utf8_unicode_ci NOT NULL,
  `hungarian` longtext COLLATE utf8_unicode_ci NOT NULL,
  `french` longtext COLLATE utf8_unicode_ci NOT NULL,
  `greek` longtext COLLATE utf8_unicode_ci NOT NULL,
  `german` longtext COLLATE utf8_unicode_ci NOT NULL,
  `italian` longtext COLLATE utf8_unicode_ci NOT NULL,
  `thai` longtext COLLATE utf8_unicode_ci NOT NULL,
  `urdu` longtext COLLATE utf8_unicode_ci NOT NULL,
  `hindi` longtext COLLATE utf8_unicode_ci NOT NULL,
  `latin` longtext COLLATE utf8_unicode_ci NOT NULL,
  `indonesian` longtext COLLATE utf8_unicode_ci NOT NULL,
  `japanese` longtext COLLATE utf8_unicode_ci NOT NULL,
  `korean` longtext COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `language`
--

INSERT INTO `language` (`phrase_id`, `phrase`, `english`, `bengali`, `spanish`, `arabic`, `dutch`, `russian`, `chinese`, `turkish`, `portuguese`, `hungarian`, `french`, `greek`, `german`, `italian`, `thai`, `urdu`, `hindi`, `latin`, `indonesian`, `japanese`, `korean`) VALUES
(1, 'admin_dashboard', 'admin dashboard', 'অ্যাডমিন ড্যাশবোর্ড', 'panel de administración', 'لوحة أجهزة القياس المشرف', 'admin dashboard', 'панели администратора', '管理面板', 'yönetici paneli', 'painel de administração', 'admin műszerfal', 'interface d\'administration', 'το admin ταμπλό', 'Admin-Dashboard', 'cruscotto Admin', 'แผงควบคุมดูแลระบบ', 'ایڈمن ڈیش بورڈ', 'व्यवस्थापक डैशबोर्ड', 'admin Dashboard', 'dashboard admin', '管理ダッシュボード', '관리자 대시 보드'),
(2, 'login', 'login', 'লগইন করুন', 'login', 'دخول', 'inloggen', 'вход', '注册', 'giriş', 'login', 'bejelentkezés', 'S\'identifier', 'σύνδεση', 'anmelden', 'login', 'เข้าสู่ระบบ', 'لاگ ان', 'लॉगिन', 'login', 'login', 'ログイン', '로그인'),
(3, 'email', 'email', 'ইমেল', 'email', 'البريد الإلكتروني', 'e-mail', 'E-mail', '电子邮件', 'e-posta', 'e-mail', 'email', 'email', 'e-mail', 'E-Mail-', 'e-mail', 'ส่งอีเมล์', 'ای میل', 'ईमेल', 'Email', 'email', 'メール', '이메일'),
(4, 'password', 'password', 'পাসওয়ার্ড', 'contraseña', 'كلمة السر', 'wachtwoord', 'пароль', '密码', 'parola', 'senha', 'jelszó', 'mot de passe', 'κωδικού πρόσβασης', 'Kennwort', 'parola d\'ordine', 'รหัสผ่าน', 'پاس ورڈ', 'पासवर्ड', 'Signum', 'kata sandi', 'パスワード', '암호'),
(5, 'forgot_password?', 'forgot password?', 'পাসওয়ার্ড ভুলে গেছেন?', '¿Olvidó su contraseña?', 'هل نسيت كلمة المرور؟', 'wachtwoord vergeten?', 'Забыли пароль?', '忘记密码？', 'şifremi unuttum?', 'Esqueceu a senha?', 'elfelejtett jelszó?', 'Mot de passe oublié?', 'Ξεχάσατε τον κωδικό σας;', 'Passwort vergessen?', 'Hai dimenticato la password?', 'ลืมรหัสผ่าน?', 'پاس ورڈ بھول گیا ہے؟', 'पासवर्ड भूल गए हैं?', 'nibh', 'lupa password?', 'パスワードを忘れた？', '비밀번호를 잊으 셨나요?'),
(6, 'account_type', 'account type', 'অ্যাকাউন্টের প্রকার', 'Tipo de cuenta', 'نوع الحساب', 'accounttype', 'тип счета', '账户类型', 'hesap türü', 'tipo de conta', 'fiók típusát', 'type de compte', 'Τύπος λογαριασμού', 'Kontotyp', 'Tipo di conto', 'ประเภทบัญชี', 'اکاؤنٹ کی قسم', 'खाता प्रकार', 'propter speciem', 'Jenis akun', '口座の種類', '계정 유형'),
(7, 'admin', 'admin', 'অ্যাডমিন', 'administración', 'مشرف', 'admin', 'Админ', '管理员', 'yönetim', 'administrador', 'admin', 'administrateur', 'το admin', 'Admin', 'Admin', 'ผู้ดูแลระบบ', 'منتظم', 'प्रशासन', 'admin', 'admin', '管理者', '관리자'),
(8, 'doctor', 'doctor', 'ডাক্তার', 'doctor', 'طبيب,en', 'dokter', 'врач,en', '医生,en', 'doktor', 'médico', 'orvos', 'médecin', 'γιατρός', 'Arzt', 'medico', 'คุณหมอ', 'ڈاکٹر', 'चिकित्सक', 'Medicus', 'dokter', '医者', '의사'),
(9, 'patient', 'patient', 'রোগীর', 'paciente', 'المريض,en', 'patiënt', 'пациент,en', '病人,en', 'hasta', 'paciente', 'beteg', 'patients', 'ασθενής', 'Patienten', 'paziente', 'ผู้ป่วย', 'مریض', 'रोगी', 'Patientes', 'pasien', '患者', '환자'),
(10, 'pharmacist', 'pharmacist', 'ফার্মাসিস্ট', 'farmacéutico', 'صيدلي,en', 'apotheker', 'фармацевт', '药剂师,en', 'eczacı', 'farmacêutico', 'gyógyszerész', 'pharmacien', 'φαρμακοποιός', 'Apotheker', 'farmacista', 'เภสัชกร', 'فارماسسٹ', 'औषध बनानेवाला', 'pharmacist', 'apoteker,en', '薬剤師', '약사,en'),
(11, 'laboratorist', 'laboratorist,eo', 'ল্যাবরেটরিস্ট', 'laboratorista', 'laboratorist', 'laboratorist', 'laboratorist', 'laboratorist', 'laboratorist', 'laboratorista', 'laboratorist', 'laboratorist', 'laboratorist', 'laboratorist', 'laboratorist,eo', 'laboratorist', 'laboratorist', 'laboratorist', 'laboratorist', 'laboratorist,eo', 'laboratorist', 'laboratorist'),
(12, 'accountant', 'accountant', 'হিসাবরক্ষক', 'contador', 'محاسب,en', 'boekhouder', 'бухгалтер', '会计,en', 'muhasebeci', 'contador', 'könyvelő', 'comptable', 'λογιστής', 'Buchhalter', 'ragioniere', 'สมุห์บัญชี', 'اکاؤنٹنٹ', 'मुनीम', 'Ratiocinatore', 'akuntan', '会計士', '회계 원'),
(13, 'monitor_hospital', 'monitor hospital', 'মনিটর হাসপাতালে', 'hospital monitor', 'مستشفى رصد,en', 'beeldscherm ziekenhuis', 'монитор больницу', '监测医院,en', 'monitör hastane', 'Monitor hospitalar', 'monitor kórházban', 'hôpital moniteur', 'οθόνη από νοσοκομείο', 'Monitor Krankenhaus', 'ospedale monitor', 'โรงพยาบาลจอ', 'مانیٹر ہسپتال', 'मॉनिटर अस्पताल', 'notes hospitalis', 'memantau rumah sakit', 'モニター病院', '모니터 병원,en'),
(14, 'nurse', 'nurse', 'নার্স', 'enfermera', 'ممرضة,en', 'verpleegster', 'медсестра', '护士,en', 'hemşire', 'enfermeira', 'nővér', 'infirmière', 'νοσοκόμα', 'Krankenschwester', 'infermiere', 'พยาบาล', 'نرس', 'नर्स', 'nutricem', 'perawat', '看護師', '간호사'),
(15, 'department', 'department', 'বিভাগ', 'departamento', 'قسم,en', 'afdeling', 'отдел', '部门,en', 'bölüm', 'departamento', 'osztály', 'département', 'τμήμα', 'Abteilung', 'Dipartimento', 'แผนก', 'محکمہ', 'विभाग', 'Department', 'departemen', '部門', '부'),
(16, 'dashboard', 'dashboard', 'ড্যাশবোর্ড', 'salpicadero', 'لوحة أجهزة القياس,en', 'dashboard', 'приборная панель', '仪表盘,en', 'gösterge paneli', 'painel de instrumentos', 'műszerfal', 'tableau de bord', 'ταμπλό', 'Armaturenbrett', 'cruscotto', 'หน้าปัด', 'ڈیش بورڈ', 'डैशबोर्ड', 'Dashboard', 'dasbor', 'ダッシュボード', '계기판,en'),
(17, 'settings', 'settings', 'সেটিংস', 'ajustes', 'إعدادات,en', 'instellingen', 'настройки', '设置,en', 'ayarları', 'definições', 'beállítások', 'réglages', 'Ρυθμίσεις', 'Einstellungen', 'impostazioni', 'การตั้งค่า', 'ترتیبات', 'सेटिंग्स', 'obitus', 'Pengaturan', '設定', '설정,en'),
(18, 'profile', 'profile', 'প্রফাইল', 'perfil', 'الملف الشخصي,en', 'profiel', 'профиль', '轮廓,en', 'profil', 'perfil', 'profil', 'profil', 'προφίλ', 'Profil', 'Profilo', 'รายละเอียด', 'پروفائل', 'प्रोफ़ाइल', 'profile', 'profil,en', 'プロフィール', '프로필'),
(19, 'settings_updated', 'settings updated', 'সেটিংস আপডেট', 'configuración actualizado', 'تحديث إعدادات', 'instellingen bijgewerkt', 'Настройки обновленной', '更新设置', 'ayarlarının güncellenmesi', 'definições atualizadas', 'beállítások frissítése', 'les paramètres mis à jour', 'Ρυθμίσεις ενημέρωση', 'Einstellungen aktualisiert', 'impostazioni aggiornate', 'การตั้งค่าการอัพเดต', 'ترتیبات کی تازہ کاری', 'सेटिंग्स अपडेट', 'occasus updated', 'pengaturan diperbarui', '設定が更新され', '설정 업데이트'),
(20, 'logout', 'logout', 'লগ আউট', 'logout', 'خروج', 'uitloggen', 'выход из системы', '注销', 'çıkış', 'sair', 'logout', 'déconnexion', 'αποσύνδεση', 'Abmeldung', 'il logout', 'ออกจากระบบ', 'لاگ آؤٹ کریں', 'लॉगआउट', 'logout', 'logout', 'ログアウト', '로그 아웃'),
(21, 'logged_out', 'logged out', 'লগ আউট', 'desconectado', 'تسجيل الخروج', 'uitgelogd', 'выход из системы', '注销', 'oturumu', 'desconectado', 'kijelentkezett', 'déconnecté', 'αποσυνδεθεί', 'abgemeldet', 'disconnesso', 'เข้าออก', 'باہر کا', 'लॉग आउट', 'de logged', 'log out', 'ログアウト', '로그 아웃'),
(22, 'reset_password', 'reset password', 'পাসওয়ার্ড রিসেট করুন', 'restablecer la contraseña', 'إعادة تعيين كلمة المرور', 'reset wachtwoord', 'Сброс пароля', '重设密码', 'parola sıfırlama', 'redefinir a senha', 'Jelszó törlése', 'réinitialiser le mot de passe', 'επαναφέρετε τον κωδικό πρόσβασης', 'Kennwort zurücksetzen', 'reimpostare la password', 'ตั้งค่ารหัสผ่าน', 'پاس ورڈ ری سیٹ', 'पासवर्ड रीसेट', 'reset Signum', 'reset password', 'パスワードをリセット', '암호를 재설정'),
(23, 'reset', 'reset', 'রিসেট করুন', 'reajustar', 'إعادة تعيين', 'reset', 'сброс', '复位', 'ayarlamak', 'restabelecer', 'vissza', 'réinitialiser', 'επαναφορά', 'Zurücksetzen', 'reimpostare', 'ตั้งใหม่', 'ری سیٹ', 'रीसेट करें', 'reset', 'ulang', 'リセット', '재설정'),
(24, 'account', 'account', 'হিসাব', 'cuenta', 'حساب', 'rekening', 'счет', '帐户', 'hesap', 'conta', 'számla', 'considération', 'λογαριασμός', 'Rechnung', 'acconto', 'บัญชี', 'اکاؤنٹ', 'खाता', 'propter', 'rekening', 'アカウント', '계정'),
(25, 'select_language', 'select language', 'ভাষা নির্বাচন করুন', 'seleccionar el idioma', 'اختر اللغة,en', 'Selecteer taal,en', 'выбрать язык,en', '选择语言,en', 'dili seçin', 'selecionar o idioma', 'Válasszon nyelvet', 'sélectionner la langue', 'επιλέξτε τη γλώσσα', 'Sprache wählen', 'selezionare la lingua', 'เลือกภาษา', 'زبان منتخب کریں', 'भाषा का चयन', 'Linguam elige', 'pilih bahasa', '言語を選択する', '언어를 선택,en'),
(26, 'panel', 'panel', 'প্যানেল', 'panel', 'لوحة', 'paneel', 'панель', '面板', 'panel', 'painel', 'bizottság', 'panneau', 'πίνακας', 'Panel', 'pannello', 'แผงหน้าปัด', 'پینل', 'पैनल', 'panel', 'panel', 'パネル', '패널'),
(27, 'view_appointment', 'view appointment', 'অ্যাপয়েন্টমেন্ট দেখতে', 'ver la cita', 'عرض التعيين', 'bekijken afspraak', 'Просмотреть встречу', '查看预约', 'randevu görmek', 'ver nomeação', 'Találkozó megtekintése', 'voir rendez-vous', 'δείτε το διορισμό', 'Termin ansehen', 'visualizzare appuntamento', 'ดูได้รับการแต่งตั้ง', 'تقرری دیکھنے کے', 'नियुक्ति देखने', 'considerabit constitutio', 'lihat penunjukan', '予定を表示', '약속보기'),
(28, 'view_payment', 'view payment', 'পেমেন্ট দেখতে', 'ver pago', 'عرض الدفع', 'bekijk betaling', 'просмотреть оплаты', '查看支付', 'ödeme görmek', 'ver o pagamento', 'megtekintése fizetés', 'voir paiement', 'δείτε πληρωμών', 'sehen Zahlung', 'visualizzare pagamento', 'ดูการชำระเงิน', 'ادائیگی کے لئیے', 'भुगतान देखने', 'considerabit solutione', 'lihat pembayaran', '支払いを表示', '지불을 볼'),
(29, 'view_bed_status', 'view bed status', 'বিছানা অবস্থা দেখতে', 'ver el estado de la cama', 'عرض حالة السرير', 'bekijk de status bed', 'View Bed статус', '查看卧床状态', 'yatak durumunu görüntülemek', 'visualizar o status de cama', 'View Bed állapot', 'afficher l\'état du lit', 'δείτε την κατάσταση κρεβάτι', 'View Bed Status', 'visualizzare lo stato del letto', 'ดูสถานะเตียง', 'بستر کی حیثیت کو دیکھنے کے', 'बिस्तर स्थिति देखने', 'considerabit status cubile', 'melihat status tidur', '床の状態を表示', '침대 상태를 확인'),
(30, 'view_blood_bank', 'view blood bank', 'ব্লাড ব্যাঙ্ক দেখতে', 'ver el banco de sangre', 'عرض بنك الدم', 'bekijk bloedbank', 'просмотреть банк крови', '查看血库', 'kan bankası görmek', 'ver banco de sangue', 'megtekintéséhez vér bank', 'voir la banque de sang', 'δείτε τράπεζα αίματος', 'sehen Blutbank', 'visualizzare banca del sangue', 'ดูธนาคารเลือด', 'بلڈ بینک کے لئیے', 'ब्लड बैंक देखने', 'considerabit sanguinem ripae', 'lihat bank darah', '血液バンクを表示', '혈액 은행보기'),
(31, 'view_medicine', 'view medicine', 'ঔষধ দেখতে', 'ver la medicina', 'نظر إلى الطب', 'bekijk geneeskunde', 'просмотреть медицине', '查看药', 'tıp görmek', 'ver medicina', 'megtekintéséhez gyógyszer', 'voir la médecine', 'δείτε το φάρμακο', 'ansehen Medizin', 'visualizzare medicina', 'ดูยา', 'دوا کو دیکھنے کے', 'दवा देखने', 'considerabit medicamentum', 'lihat obat', '薬を見る', '약을 보려면'),
(32, 'view_operation', 'view operation', 'অপারেশন দেখতে', 'ver la operación', 'عرض عملية', 'bekijk operatie', 'Отображение работы', '查看操作', 'çalışma görmek', 'visualizar operação', 'megtekinteni működés', 'voir opération', 'δείτε τη λειτουργία', 'anzuzeigen Betrieb', 'visualizzare il funzionamento', 'ดูการดำเนินงาน', 'آپریشن دیکھنے کے', 'आपरेशन देखने', 'considerabit operatione', 'melihat operasi', '操作を表示', '작업을 보려면'),
(33, 'view_birth_report', 'view birth report', 'আমার রিপোর্ট', 'ver el informe de nacimiento', 'عرض التقرير الميلاد', 'bekijk geboorte rapport', 'просмотреть отчет рождения', '查看出生申报', 'Doğum raporunu görüntülemek', 'ver relatório de nascimento', 'megtekintéséhez születési jelentés', 'voir rapport de naissance', 'προβάλετε την αναφορά γέννησης', 'sehen Geburtsdatum Bericht', 'visualizzare il report di nascita', 'ดูรายงานการเกิด', 'آپ کی رپورٹ دیکھنے کے', 'जन्म रिपोर्ट देखने', 'considerabit nativitatis auditui', 'melihat laporan kelahiran', '出産レポートを表示', '출생 보고서를 보려면'),
(34, 'view_death_report', 'view death report', 'মৃত্যুর রিপোর্ট', 'ver el informe de la muerte', 'عرض تقرير الوفاة', 'bekijk dood rapport', 'просмотреть отчет смерти', '查看死亡报告', 'ölüm raporunu görüntülemek', 'ver relatório morte', 'a halált jelentés', 'voir rapport de la mort', 'προβάλετε την αναφορά του θανάτου', 'sehen Tod Bericht', 'visualizzare il report di morte', 'ดูรายงานการเสียชีวิต', 'موت کی رپورٹ دیکھنے کے', 'मौत रिपोर्ट देखने', 'considerabit mortis nuntium', 'melihat laporan kematian', '死のレポートを表示', '죽음의 보고서를 보려면'),
(35, 'manage_email_template', 'manage email template', 'ইমেইল টেমপ্লেট পরিচালনা', 'gestión de plantilla de correo electrónico', 'إدارة قالب البريد الإلكتروني', 'beheren e-mail template', 'Шаблон управления электронной почтой', '管理电子邮件模板', 'e-posta şablonu yönetmek', 'gerenciar modelo de e-mail', 'kezelni e-mail sablon', 'gérer modèle de courriel', 'διαχείριση πρότυπο email', 'Verwalten von E-Vorlage', 'gestione modello e-mail', 'แม่แบบการจัดการอีเมล์', 'ای میل سانچے انتظام', 'ईमेल टेम्पलेट का प्रबंधन', 'Morbi luctus curo', 'mengelola email template', '電子メールテンプレートを管理', '전자 메일 서식 파일 관리'),
(36, 'manage_noticeboard', 'manage noticeboard', 'নোটিশবোর্ড পরিচালনা', 'gestionar tablón de anuncios', 'إدارة اللافتة', 'beheren prikbord', 'управлять доске объявлений', '管理布告', 'panosuna yönetmek', 'gerenciar avisos', 'kezelni üzenőfalán', 'gérer panneau d\'', 'διαχείριση Ανακοινώσεων', 'verwalten noticeboard', 'gestire bacheca', 'จัดการ noticeboard', 'noticeboard کا انتظام', 'नोटिसबोर्ड प्रबंधन', 'noticeboard curo', 'mengelola pengumuman', '掲示板を管理', '공지 사항 more 관리'),
(37, 'system_settings', 'system settings', 'সিস্টেম সেটিংস', 'configuración del sistema', 'إعدادات النظام', 'systeeminstellingen', 'настройки системы', '系统设置', 'sistem ayarlarını', 'configurações do sistema', 'rendszerbeállításokat', 'les paramètres du système', 'ρυθμίσεις του συστήματος', 'Systemeinstellungen', 'impostazioni di sistema', 'การตั้งค่าระบบ', 'سسٹم کی ترتیبات', 'प्रणाली सेटिंग्स', 'ratio occasus', 'pengaturan sistem', 'システム設定', '시스템 설정'),
(38, 'manage_language', 'manage language', 'ভাষা পরিচালনা', 'gestionar idioma', 'إدارة اللغة', 'beheren taal', 'управлять языком', '管理语言', 'dil yönetmek', 'gerenciar linguagem', 'kezelni nyelv', 'gérer langue', 'διαχείριση γλώσσα', 'verwalten Sprache', 'gestire lingua', 'จัดการภาษา', 'زبان کا انتظام', 'भाषा का प्रबंधन', 'Linguam curo', 'mengelola bahasa', '言語を管理', '언어 관리'),
(39, 'backup_restore', 'backup restore', 'পুনঃস্থাপন ব্যাকআপ', 'Restaurar copia de seguridad', 'استعادة النسخ الاحتياطي', 'backup terugzetten', 'восстановление резервной копии', '备份还原', 'geri yedekleme', 'Restauração de Backup', 'Backup Restore', 'Backup Restore', 'επαναφοράς αντιγράφων ασφαλείας', 'Backup wiederherstellen', 'Backup Restore', 'การสำรองข้อมูลเรียกคืน', 'بحال بیک اپ', 'बहाल बैकअप', 'tergum quod restituo', 'backup restore', 'バックアップのリストア', '복원 백업'),
(40, 'appointment', 'appointment', 'এপয়েন্টমেন্ট', 'nombramiento', 'موعد', 'afspraak', 'назначение', '任命', 'randevu', 'nomeação', 'kinevezés', 'rendez-vous', 'ραντεβού', 'Ernennung', 'appuntamento', 'การแต่งตั้ง', 'تقرری', 'नियुक्ति', 'Nominatio', 'penunjukan', '任命', '약속'),
(41, 'payment', 'payment', 'প্রদান', 'pago', 'دفع', 'betaling', 'оплата', '付款', 'ödeme', 'pagamento', 'fizetés', 'paiement', 'πληρωμή', 'Bezahlung', 'pagamento', 'การชำระเงิน', 'ادائیگی', 'भुगतान', 'solucionis', 'pembayaran', '支払い', '지불'),
(42, 'blood_bank', 'blood bank', 'ব্লাড ব্যাঙ্ক', 'banco de sangre', 'بنك الدم', 'bloedbank', 'банк крови', '血库', 'kan bankası', 'banco de sangue', 'vérbank', 'la banque de sang', 'τράπεζα αίματος', 'Blutbank', 'banca del sangue', 'ธนาคารเลือด', 'بلڈ بینک', 'रक्त बैंक', 'sanguinem ripam', 'bank darah', '血液バンク', '혈액 은행'),
(43, 'medicine', 'medicine', 'ঔষধ', 'medicina', 'دواء', 'geneeskunde', 'медицина', '医药', 'tıp', 'medicina', 'orvostudomány', 'médecine', 'ιατρική', 'Medizin', 'medicina', 'ยา', 'دوا', 'दवा', 'medicine', 'obat', '医学', '의학'),
(44, 'operation_report', 'operation report', 'অপারেশন পরিচালনা', 'confirmación de la operación', 'تقرير العملية', 'bediening rapport', 'отчет о работе', '操作报告', 'çalışma raporu', 'relatório operação', 'működéséről szóló jelentés', 'rapport d\'opération', 'αναφορά λειτουργίας', 'OP-Bericht', 'rapporto sul funzionamento', 'รายงานการดำเนินงาน', 'آپریشن کی رپورٹ', 'आपरेशन रिपोर्ट', 'operatio auditui', 'Laporan operasi', '動作報告', '운영 보고서'),
(45, 'birth_report', 'birth report', 'আমার রিপোর্ট', 'informe nacimiento', 'تقرير الميلاد', 'geboorte rapport', 'Отчет рождения', '出生申报', 'doğum raporu', 'relatório de nascimento', 'születési jelentés', 'rapport à la naissance', 'Έκθεση Γέννησης', 'Geburt Bericht', 'relazione di nascita', 'รายงานการเกิด', 'آپ کی رپورٹ', 'जन्म रिपोर्ट', 'fama nativitate', 'Laporan lahir', '出生届', '출생 보고서'),
(46, 'death_report', 'death report', 'মৃত্যুর প্রতিবেদন', 'informe de la muerte', 'تقرير الموت', 'dood rapport', 'смерть отчет', '死亡报告', 'ölüm raporu', 'relatório morte', 'halál jelentés', 'Rapport de mort', 'έκθεση του θανάτου', 'Tod Bericht', 'rapporto di morte', 'รายงานการเสียชีวิต', 'موت رپورٹ', 'मौत रिपोर्ट', 'fama mortis', 'Laporan kematian', '死亡届', '사망 보고서'),
(47, 'bed_allotment', 'bed allotment', 'বিছানা অ্যালটমেন্ট', 'asignación de cama', 'تخصيص سرير', 'bed toewijzing', 'кровать выделение', '床上配发', 'yatak tahsis', 'cama loteamento', 'ágy elosztás', 'attribution de lit', 'κατανομής κρεβάτι', 'Bett Zuteilung', 'letto riparto', 'จัดสรรเตียง', 'بستر الاٹمنٹ', 'बिस्तर आवंटन', 'stratum sorti', 'tidur penjatahan', 'ベッド割当', '침대 할당'),
(48, 'noticeboard', 'noticeboard', 'নোটিশবোর্ড', 'noticeboard', 'اللافتة', 'prikbord', 'доска объявлений', '布告', 'panosuna', 'NOTICEBOARD', 'üzenőfalán', 'Panneau d\'affichage', 'Ανακοινώσεων', 'Schwarzes Brett', 'bacheca', 'noticeboard', 'noticeboard', 'Noticeboard', 'noticeboard', 'pengumuman', '掲示板', '공지 사항 more'),
(49, 'language', 'language', 'ভাষা', 'idioma', 'لغة', 'taal', 'язык', '语', 'dil', 'linguagem', 'nyelv', 'langue', 'γλώσσα', 'Sprache', 'lingua', 'ภาษา', 'زبان', 'भाषा', 'Linguam', 'bahasa', '言語', '언어'),
(50, 'backup', 'backup', 'ব্যাকআপ', 'copia de seguridad', 'النسخ الاحتياطي', 'backup', 'резервное копирование', '备份', 'yedekleme', 'fazer backup', 'hát', 'sauvegarder', 'αντιγράφων ασφαλείας', 'Backup', 'eseguire il backup', 'การสำรองข้อมูล', 'بیک اپ', 'बैकअप', 'tergum', 'backup', 'バックアップ', '백업'),
(51, 'calendar_schedule', 'calendar schedule', 'ক্যালেন্ডার সময়সূচী', 'horario de calendario', 'جدول التقويم', 'kalender schema', 'календарный план', '日历日程', 'takvim programı', 'calendário da programação', 'naptári ütemezés', 'calendrier calendrier', 'ημερολογιακό πρόγραμμα', 'Kalender Zeitplan', 'calendario calendario', 'ตารางปฏิทิน', 'کیلنڈر شیڈول', 'कैलेंडर अनुसूची', 'Calendarium cedula', 'jadwal kalender', 'カレンダースケジュール', '캘린더 일정'),
(52, 'manage_department', 'manage department', 'ডিপার্টমেন্ট পরিচালনা', 'gestionar departamento', 'إدارة قسم', 'beheren afdeling', 'управлять отделом', '管理部门', 'bölümü yönetmek', 'gerenciar departamento', 'kezelni osztály', 'gérer département', 'διαχειρίζεται το τμήμα', 'Verwaltung Abteilung', 'gestire reparto', 'จัดการแผนก', 'محکمہ کا انتظام', 'विभाग का प्रबंधन', 'department curo', 'mengelola departemen', '部門を管理', '부서 관리'),
(53, 'department_list', 'department list', 'বিভাগ তালিকা', 'Lista departamento', 'قائمة وزارة', 'afdeling lijst', 'Отдел список', '部门列表', 'bölüm listesi', 'Lista de departamento', 'osztály lista', 'Liste département', 'Τμήμα λίστα', 'Abteilung Liste', 'Lista reparto', 'รายการแผนก', 'محکمہ کی فہرست', 'विभाग की सूची', 'department album', 'Daftar departemen', '部門リスト', '부서 목록'),
(54, 'add_department', 'add department', 'বিভাগ যোগ করুন', 'añadir departamento', 'إضافة قسم', 'add afdeling', 'добавить отделе', '新增部门', 'bölümü ekleyin', 'adicionar departamento', 'add osztály', 'ajouter département', 'προσθέστε το τμήμα', 'Abteilung hinzufügen', 'aggiungere reparto', 'เพิ่มแผนก', 'محکمہ شامل', 'विभाग जोड़ना', 'addere dolor', 'menambah organisasi', '部門を追加', '부서를 추가'),
(55, 'department_name', 'department name', 'বিভাগের নাম', 'nombre del departamento', 'اسم القسم', 'afdelingsnaam', 'название отдела', '部门名称', 'bölüm adı', 'nome do departamento', 'részleg nevét', 'nom du département', 'Τμήμα όνομα', 'Name der Abteilung', 'nome del reparto', 'ชื่อแผนก', 'محکمہ کا نام', 'विभाग का नाम', 'nomen department', 'nama departemen', '部署名', '부서 이름'),
(56, 'description', 'description', 'বর্ণনা', 'descripción', 'وصف', 'beschrijving', 'описание', '描述', 'tanımlama', 'descrição', 'leírás', 'Description', 'περιγραφή', 'Beschreibung', 'descrizione', 'ลักษณะ', 'تفصیل', 'विवरण', 'description', 'deskripsi', '説明', '기술'),
(57, 'options', 'options', 'অপশন', 'Opciones', 'خيارات', 'opties', 'опции', '选项', 'seçenekleri', 'opções', 'lehetőségek', 'les options', 'Επιλογές', 'Optionen', 'opzioni', 'ตัวเลือก', 'اختیارات', 'विकल्प', 'options', 'Pilihan', 'オプション', '옵션'),
(58, 'edit', 'edit', 'সম্পাদনা করুন', 'editar', 'تعديل', 'uitgeven', 'редактировать', '编辑', 'düzenleme', 'editar', 'szerkeszt', 'éditer', 'επεξεργαστείτε', 'bearbeiten', 'modificare', 'แก้ไข', 'ترمیم کریں', 'संपादित', 'emendo', 'mengedit', '編集', '편집'),
(59, 'delete', 'delete', 'মুছে দিন', 'borrar', 'حذف', 'verwijderen', 'удалять', '删除', 'silmek', 'excluir', 'töröl', 'supprimer', 'διαγραφή', 'löschen', 'cancella', 'ลบ', 'خارج کر دیں', 'हटाना', 'delete', 'menghapus', '削除する', '삭제'),
(60, 'department_description', 'department description', 'বিভাগ বর্ণনা', 'Descripción del departamento', 'وصف قسم', 'afdeling beschrijving', 'Отдел описание', '科室介绍', 'Bölüm açıklaması', 'descrição departamento', 'osztály leírás', 'Description département', 'Τμήμα περιγραφή', 'Abteilung Beschreibung', 'Descrizione del reparto', 'คำอธิบายของแผนก', 'محکمہ تفصیل', 'विभाग विवरण', 'hac descriptione', 'deskripsi departemen', '部門の説明', '부서 소개'),
(61, 'manage_doctor', 'manage doctor', 'ডাক্তার পরিচালনা', 'gestionar médico', 'إدارة الطبيب', 'beheren arts', 'управлять врача', '医生管理', 'doktor yönetmek', 'gerenciar médico', 'kezelését orvos', 'gérer médecin', 'διαχειρίζεται το γιατρό', 'verwalten Arzt', 'gestire medico', 'จัดการแพทย์', 'ڈاکٹر کا انتظام', 'डॉक्टर का प्रबंधन', 'Medicus curo', 'mengelola dokter', '医師を管理', '의사 관리'),
(62, 'doctor_list', 'doctor list', 'ডাক্তারের তালিকা', 'Lista médico', 'قائمة الطبيب', 'arts lijst', 'Врач список', '医生名单', 'doktor listesi', 'lista médico', 'orvos lista', 'liste de racle', 'Λίστα γιατρό', 'Arzt-Liste', 'elenco medico', 'รายชื่อแพทย์', 'ڈاکٹر فہرست', 'डॉक्टर सूची', 'Medicus album', 'Daftar dokter', '医師リスト', '의사 목록'),
(63, 'add_doctor', 'add doctor', 'ডাক্তার যোগ করুন', 'añadir médico', 'طبيب جديد', 'voegen arts', 'добавить врача', '加医生', 'doktor ekleyin', 'adicionar médico', 'hozzá orvos', 'ajouter médecin', 'προσθέστε το γιατρό', 'Arzt hinzufügen', 'aggiungere medico', 'เพิ่มแพทย์', 'ڈاکٹر شامل', 'डॉक्टर जोड़', 'adde medicum', 'menambah tenaga dokter', '医師を追加', '의사 추가'),
(64, 'doctor_name', 'doctor name', 'ডাক্তারের নাম', 'Nombre del médico', 'اسم الطبيب', 'Naam arts', 'Врач название', '医生姓名', 'doktor adı', 'Nome do médico', 'orvos név', 'Nom du médecin', 'Όνομα γιατρό', 'Arzt Namen', 'Nome del medico', 'ชื่อแพทย์', 'ڈاکٹر کا نام', 'डॉक्टर का नाम', 'medicus nomen', 'Nama dokter', '医師名', '의사 이름'),
(65, 'name', 'name', 'নাম', 'nombre', 'اسم', 'naam', 'название', '名', 'ad', 'nome', 'név', 'Nom', 'όνομα', 'Name', 'nome', 'ชื่อ', 'نام', 'नाम', 'nóminis', 'nama', '名前', '이름'),
(66, 'address', 'address', 'ঠিকানা', 'dirección', 'عنوان', 'adres', 'адрес', '地址', 'adres', 'endereço', 'cím', 'Adresse', 'διεύθυνση', 'Anschrift', 'indirizzo', 'ที่อยู่', 'ایڈریس', 'पता', 'inscriptio', 'alamat', 'アドレス', '주소'),
(67, 'phone', 'phone', 'ফোন', 'teléfono', 'هاتف', 'telefoon', 'телефон', '电话', 'telefon', 'telefone', 'telefon', 'téléphone', 'τηλέφωνο', 'telefonieren', 'telefono', 'โทรศัพท์', 'فون', 'फ़ोन', 'phone', 'telepon', '電話', '전화'),
(68, 'manage_patient', 'manage patient', 'রোগীর পরিচালনা', 'gestionar paciente', 'إدارة المريض', 'beheren patiënt', 'управлять пациента', '管理病人', 'hastanın tedavisini düzenleyin', 'gerenciar paciente', 'kezelni beteg', 'gérer les patients', 'διαχείριση των ασθενών', 'Patienten verwalten', 'gestione del paziente', 'บริหารจัดการผู้ป่วย', 'مریض کا انتظام', 'रोगी प्रबंधन', 'patiens aget', 'mengelola pasien', '患者を管理', '환자 관리'),
(69, 'patient_list', 'patient list', 'রোগীর তালিকা', 'lista de pacientes', 'قائمة المريض', 'patiënt lijst', 'Список пациентов', '病人列表', 'Hasta listesi', 'lista de pacientes', 'beteglistát', 'liste des patients', 'λίστα ασθενών', 'Patientenliste', 'elenco dei pazienti', 'รายชื่อผู้ป่วย', 'مریض کی فہرست', 'रोगी सूची', 'Patientes album', 'daftar pasien', '患者リスト', '환자 목록'),
(70, 'add_patient', 'add patient', 'রোগীর যোগ করুন', 'añadir paciente', 'إضافة المريض', 'voegen patiënt', 'добавить пациента', '增加病人', 'Hasta ekleyin', 'adicionar paciente', 'hozzá beteg', 'ajouter patients', 'προσθήκη ασθενούς', 'Patienten hinzufügen', 'aggiungere paziente', 'เพิ่มผู้ป่วย', 'مریض شامل', 'रोगी जोड़ना', 'adde patientes', 'menambahkan pasien', '患者を追加', '환자 추가'),
(71, 'patient_name', 'patient name', 'রোগীর নাম', 'Nombre del paciente', 'اسم المريض', 'naam van de patiënt', 'имя пациента', '患者姓名', 'hasta adı', 'nome do paciente', 'páciens neve', 'le nom du patient', 'το όνομα του ασθενούς', 'Patientennamen', 'nome del paziente', 'ชื่อผู้ป่วย', 'مریض کا نام', 'रोगी का नाम', 'Patientes nomine', 'Nama pasien', '患者名', '환자의 이름'),
(72, 'age', 'age', 'বয়স', 'edad', 'عمر', 'leeftijd', 'возраст', '年龄', 'yaş', 'idade', 'életkor', 'âge', 'ηλικία', 'Alter', 'età', 'อายุ', 'عمر', 'उम्र', 'saeculi', 'usia', '年齢', '나이'),
(73, 'sex', 'sex', 'যৌন', 'sexo', 'جنس', 'seks', 'секс', '性别', 'seks', 'sexo', 'szex', 'sexe', 'φύλο', 'Sex', 'sesso', 'เพศ', 'جنسی تعلقات', 'लिंग', 'sexum', 'seks', 'セックス', '섹스'),
(74, 'blood_group', 'blood group', 'রক্তের বিভাগ', 'grupo sanguíneo', 'فصيلة الدم', 'bloedgroep', 'группа крови', '血型', 'kan grubu', 'grupo sanguíneo', 'vércsoport', 'groupe sanguin', 'ομάδα αίματος', 'Blutgruppe', 'gruppo sanguigno', 'กรุ๊ปเลือด', 'خون کے گروپ', 'रक्त वर्ग', 'sanguine coetus', 'golongan darah', '血液型', '혈액형'),
(75, 'birth_date', 'birth date', 'আমার জন্ম', 'fecha de nacimiento', 'تاريخ الميلاد', 'geboortedatum', 'Дата рождения', '出生日期', 'doğum tarihi', 'data de nascimento', 'születési dátum', 'date de naissance', 'ημερομηνία γέννησης', 'Geburtsdatum', 'Data di nascita', 'วันเดือนปีเกิด', 'پیدائش کی تاریخ', 'जन्म तिथि', 'date nativitatis', 'tanggal lahir', '生年月日', '생년월일'),
(76, 'male', 'male', 'পুরুষ', 'macho', 'ذكر', 'mannelijk', 'мужской', '男性', 'erkek', 'masculino', 'férfi', 'masculin', 'αρσενικός', 'männlich', 'maschio', 'ชาย', 'کھنگالیں', 'नर', 'masculus', 'laki-laki', '男性', '남성'),
(77, 'female', 'female', 'মহিলা', 'femenino', 'أنثى', 'vrouw', 'женский', '女性', 'kadın', 'feminino', 'női', 'féminin', 'θηλυκός', 'weiblich', 'femminile', 'หญิง', 'خواتین', 'महिला', 'feminam', 'perempuan', '女性', '여성'),
(78, 'manage_nurse', 'manage nurse', 'নার্স পরিচালনা', 'gestionar la enfermera', 'إدارة ممرضة', 'beheren verpleegkundige', 'управлять медсестры', '护士管理', 'hemşire yönetmek', 'gerenciar enfermeira', 'kezelni nővér', 'gérer infirmière', 'διαχείριση νοσοκόμα', 'verwalten Krankenschwester', 'gestire infermiera', 'จัดการพยาบาล', 'نرس کا انتظام', 'नर्स का प्रबंधन', 'nutrix curo', 'mengelola perawat', '看護師を管理', '간호사 관리'),
(79, 'nurse_list', 'nurse list', 'নার্স তালিকা', 'Lista de enfermera', 'قائمة ممرضة', 'verpleegkundige lijst', 'Медсестра список', '护士名单', 'hemşire liste', 'lista enfermeira', 'nővér lista', 'infirmière liste', 'Λίστα νοσοκόμα', 'Krankenschwester Liste', 'Lista infermiera', 'รายการพยาบาล', 'نرس کی فہرست', 'नर्स सूची', 'nutrix album', 'daftar perawat', '看護師のリスト', '간호사 목록'),
(80, 'add_nurse', 'add nurse', 'নার্স যোগ করুন', 'añadir enfermera', 'إضافة ممرضة', 'voegen verpleegkundige', 'добавить медсестры', '增加护士', 'hemşire ekleyin', 'adicionar enfermeira', 'hozzá nővér', 'ajouter infirmière', 'προσθήκη νοσοκόμα', 'Krankenschwester hinzufügen', 'aggiungere infermiera', 'เพิ่มพยาบาล', 'نرس شامل', 'नर्स जोड़', 'adde nutricem', 'tambahkan perawat', '看護師を追加', '간호사 추가'),
(81, 'nurse_name', 'nurse name', 'নার্স নাম', 'nombre de la enfermera', 'اسم ممرضة', 'naam verpleegkundige', 'Медсестра название', '护士的名字', 'hemşire adı', 'Nome enfermeira', 'nővér név', 'nom de l\'infirmière', 'Όνομα νοσοκόμα', 'Krankenschwester Namen', 'Nome infermiera', 'ชื่อพยาบาล', 'نرس کا نام', 'नर्स का नाम', 'nomine nutricis', 'Nama perawat', '看護師名', '간호사 이름'),
(82, 'manage_pharmacist', 'manage pharmacist', 'ফার্মাসিস্ট পরিচালনা', 'gestionar farmacéutico', 'إدارة الصيدلي', 'beheren apotheker', 'управлять фармацевт', '管理药剂师', 'eczacı yönetmek', 'gerenciar farmacêutico', 'kezelni gyógyszerész', 'gérer pharmacien', 'διαχείριση φαρμακοποιό', 'verwalten Apotheker', 'gestire farmacista', 'จัดการเภสัชกร', 'فارماسسٹ کا انتظام', 'फार्मासिस्ट का प्रबंधन', 'pharmacist curo', 'mengelola apoteker', '薬剤師を管理', '약사 관리'),
(83, 'pharmacist_list', 'pharmacist list', 'ফার্মাসিস্ট তালিকা', 'Lista farmacéutico', 'قائمة الصيدلي', 'apotheker lijst', 'Фармацевт список', '药剂师列表', 'eczacı liste', 'lista farmacêutico', 'gyógyszerész lista', 'liste de pharmacien', 'Λίστα φαρμακοποιό', 'Apotheker Liste', 'Lista farmacista', 'รายการเภสัชกร', 'فارماسسٹ فہرست', 'फार्मासिस्ट सूची', 'pharmacist album', 'Daftar apoteker', '薬剤師のリスト', '약사 목록'),
(84, 'add_pharmacist', 'add pharmacist', 'ফার্মাসিস্ট যোগ করুন', 'añadir farmacéutico', 'إضافة الصيدلي', 'voegen apotheker', 'добавить фармацевта', '加药剂师', 'eczacı ekleyin', 'adicionar farmacêutico', 'hozzá gyógyszerészét', 'ajouter pharmacien', 'προσθέστε το φαρμακοποιό', 'Apotheker hinzufügen', 'aggiungere il farmacista', 'เพิ่มเภสัชกร', 'فارماسسٹ شامل', 'फार्मासिस्ट जोड़', 'adde pharmacopola', 'menambahkan apoteker', '薬剤師を追加', '약사 추가'),
(85, 'pharmacist_name', 'pharmacist name', 'ফার্মাসিস্ট নাম', 'Nombre farmacéutico', 'اسم الصيدلي', 'naam apotheker', 'Фармацевт название', '药剂师名称', 'eczacı adı', 'Nome farmacêutico', 'gyógyszerész név', 'Nom du pharmacien', 'το όνομα του φαρμακοποιού', 'Apotheker Namen', 'Nome del farmacista', 'ชื่อเภสัชกร', 'فارماسسٹ کا نام', 'फार्मासिस्ट के नाम', 'pharmacist nomine', 'Nama apoteker', '薬剤師名', '약사 이름'),
(86, 'manage_laboratorist', 'manage laboratorist,eo', 'ল্যাবরেটরিস্ট পরিচালনা', 'gestionar laboratorista', 'إدارة laboratorist', 'beheren laboratorist', 'управлять laboratorist', '管理laboratorist“', 'laboratorist yönetmek', 'gerenciar laboratorista', 'kezelni laboratorist', 'gérer laboratorist', 'διαχείριση laboratorist', 'verwalten laboratorist', 'gestire laboratorist', 'จัดการ laboratorist', 'laboratorist کا انتظام', 'laboratorist प्रबंधन', 'laboratorist curo', 'mengelola laboratorist', 'laboratoristを管理', 'laboratorist 관리'),
(87, 'laboratorist_list', 'laboratorist list,eo', 'ল্যাবরেটরিস্ট তালিকা', 'Lista laboratorista', 'قائمة laboratorist', 'laboratorist lijst', 'laboratorist список', 'laboratorist列表', 'laboratorist liste', 'lista laboratorista', 'laboratorist lista', 'Liste d\'laboratorist', 'Λίστα laboratorist', 'laboratorist Liste', 'Lista laboratorist', 'รายการ laboratorist', 'laboratorist فہرست', 'laboratorist सूची', 'laboratorist album', 'Daftar laboratorist', 'laboratoristリスト', 'laboratorist 목록'),
(88, 'add_laboratorist', 'add laboratorist,eo', 'ল্যাবরেটরিস্ট যোগ করুন', 'añadir laboratorista', 'إضافة laboratorist', 'voegen laboratorist', 'добавить laboratorist', '添加laboratorist“', 'laboratorist ekleyin', 'adicionar laboratorista', 'hozzá laboratorist', 'ajouter laboratorist', 'προσθήκη laboratorist', 'laboratorist hinzufügen', 'aggiungere laboratorist', 'เพิ่ม laboratorist', 'laboratorist شامل', 'laboratorist जोड़', 'adde laboratorist', 'tambahkan laboratorist', 'laboratoristを追加', 'laboratorist 추가'),
(89, 'laboratorist_name', 'laboratorist name,sl', 'ল্যাবরেটরিস্ট নাম', 'Nombre laboratorista', 'اسم laboratorist', 'naam laboratorist', 'laboratorist название', 'laboratorist名', 'laboratorist adı', 'nome laboratorista', 'laboratorist név', 'nom de laboratorist', 'Όνομα laboratorist', 'laboratorist Namen', 'Nome laboratorist', 'ชื่อ laboratorist', 'laboratorist نام', 'laboratorist नाम', 'nomen laboratorist', 'Nama laboratorist', 'laboratorist名', 'laboratorist 이름'),
(90, 'manage_accountant', 'manage accountant', 'হিসাবরক্ষক পরিচালনা', 'gestión contable', 'إدارة محاسب', 'beheren accountant', 'управлять бухгалтера', '管理会计师', 'muhasebeci yönetmek', 'gerenciar contador', 'kezelni könyvelő', 'gérer comptable', 'διαχείριση λογιστή', 'Buchhalter verwalten', 'gestione contabile', 'การจัดการบัญชี', 'اکاؤنٹنٹ کا انتظام', 'लेखाकार प्रबंधन', 'Ratiocinatore curo', 'mengelola akuntan', '会計士を管理', '회계 관리'),
(91, 'accountant_list', 'accountant list', 'হিসাবরক্ষক তালিকা', 'Lista de contador', 'قائمة محاسب', 'accountant lijst', 'бухгалтером список', '会计师名单', 'muhasebeci liste', 'Lista de contador', 'könyvelő lista', 'Liste comptable', 'Λίστα λογιστή', 'Buchhalter Liste', 'Lista ragioniere', 'รายการบัญชี', 'اکاؤنٹنٹ فہرست', 'लेखाकार सूची', 'Ratiocinatore album', 'Daftar akuntan', '会計士リスト', '회계사 목록'),
(92, 'add_accountant', 'add accountant', 'হিসাবরক্ষক যোগ করুন', 'añadir contador', 'إضافة محاسب', 'voegen accountant', 'добавить бухгалтера', '添加会计师', 'muhasebeci ekleyin', 'adicionar contador', 'hozzá könyvelő', 'ajouter comptable', 'προσθήκη λογιστή', 'Buchhalter hinzufügen', 'aggiungere ragioniere', 'เพิ่มบัญชี', 'اکاؤنٹنٹ شامل', 'एकाउंटेंट जोड़ना', 'adde Ratiocinatore', 'tambahkan akuntan', '会計士を追加', '회계사 추가'),
(93, 'accountant_name', 'accountant name', 'হিসাবরক্ষক নাম', 'Nombre del contador', 'اسم محاسب', 'naam accountant', 'бухгалтером название', '会计师名称', 'muhasebeci adı', 'Nome do contador', 'könyvelő név', 'Nom de l\'expert-comptable', 'Όνομα λογιστή', 'Buchhalter Namen', 'Nome ragioniere', 'ชื่อบัญชี', 'اکاؤنٹنٹ کا نام', 'लेखाकार नाम', 'nomen Ratiocinatore', 'Nama akuntan', '会計士名', '회계사 이름'),
(94, 'phrase_list', 'phrase list', 'ফ্রেজ তালিকা', 'lista de frases', 'قائمة العبارة', 'woordgroepenlijst', 'список фраз', '短语列表', 'ifade liste', 'lista de frases', 'kifejezés lista', 'liste de phrase', 'Λίστα φράση', 'Phrase Liste', 'elenco frase', 'รายการวลี', 'جملہ فہرست', 'वाक्यांश सूची', 'phrase album', 'Daftar frase', 'フレーズリスト', '구문 목록'),
(95, 'add_phrase', 'add phrase', 'শব্দবন্ধ যোগ করুন', 'añadir una frase', 'إضافة العبارة', 'add zin', 'Добавить фразу', '加短语', 'ifade ekleme', 'adicionar frase', 'add kifejezés', 'ajouter phrase', 'Προσθήκη φράσης', 'Phrase hinzufügen', 'aggiungere la frase', 'เพิ่มวลี', 'فقرہ شامل کریں', 'वाक्यांश जोड़ना', 'addere phrase', 'menambahkan frase', 'フレーズを追加', '문구를 추가'),
(96, 'add_language', 'add language', 'ভাষা যুক্ত', 'añadir el idioma', 'إضافة لغة', 'add taal', 'добавить язык', '新增语言', 'dil eklemek', 'adicionar linguagem', 'nyelv hozzáadása', 'ajouter langue', 'προσθήκη γλώσσας', 'Sprache hinzufügen', 'aggiungere la lingua', 'เพิ่มภาษา', 'زبان کا اضافہ', 'भाषा जोड़ना', 'Linguam addere', 'menambah bahasa', '言語を追加する', '언어를 추가'),
(97, 'phrase', 'phrase', 'ফ্রেজ', 'frase', 'العبارة', 'frase', 'фраза', '短语', 'ifade', 'frase', 'kifejezés', 'phrase', 'φράση', 'Begriff', 'Frase', 'วลี', 'جملے', 'वाक्यांश', 'phrase', 'frasa', 'フレーズ', '구'),
(98, 'delete_language', 'delete language', 'ভাষা মুছে দিন', 'eliminar el idioma', 'لغة حذف', 'taal verwijderen', 'удалить язык', '删除语言', 'dil silmek', 'excluir linguagem', 'törlése nyelv', 'supprimer langue', 'διαγραφή γλώσσα', 'löschen Sprache', 'eliminare lingua', 'ลบภาษา', 'زبان کو خارج کر دیں', 'भाषा को हटाना', 'Linguam delete', 'menghapus bahasa', '言語を削除する', '언어를 삭제'),
(99, 'update_phrase', 'update phrase', 'ফ্রেজ আপডেট', 'actualizar frase', 'تحديث العبارة', 'updaten zin', 'обновления фразу', '更新短语', 'ifade güncelleme', 'atualizar frase', 'frissítés kifejezés', 'mettre à jour phrase', 'ενημέρωση φράση', 'aktualisieren Phrase', 'aggiornare frase', 'ปรับปรุงวลี', 'جملہ اپ ڈیٹ', 'वाक्यांश अद्यतन', 'phrase update', 'memperbarui frase', 'フレーズを更新', '문구를 업데이트'),
(100, 'time', 'time', 'সময়', 'tiempo', 'وقت', 'tijd', 'время', '时间', 'zaman', 'tempo', 'idő', 'heure', 'χρόνος', 'Zeit', 'volta', 'เวลา', 'وقت', 'समय', 'tempus', 'waktu', '時間', '시간'),
(101, 'amount', 'amount', 'পরিমাণ', 'cantidad', 'كمية', 'bedrag', 'количество', '量', 'miktar', 'quantidade', 'mennyiség', 'montant', 'ποσό', 'Betrag', 'importo', 'จำนวน', 'رقم', 'राशि', 'moles', 'jumlah', '額', '양'),
(102, 'payment_type', 'payment type', 'পেমেন্ট টাইপ', 'forma de pago', 'نوع الدفع', 'soort betaling', 'тип оплаты', '支付类型', 'ödeme türü', 'tipo de pagamento', 'fizetési módot', 'Type de paiement', 'Τύπος πληρωμής', 'Art der Bezahlung', 'Tipo di pagamento', 'ประเภทการชำระเงิน', 'ادائیگی کی قسم', 'भुगतान के प्रकार', 'type solutione', 'jenis pembayaran', '支払いタイプ', '지불 유형'),
(103, 'transaction_id', 'transaction id', 'লেনদেন আইডি', 'identificación de la transacción', 'رقم المعاملات', 'transaction id', 'ID транзакции', '事务ID', 'işlem kimliği', 'ID da transação', 'tranzakció id', 'Identifiant de transaction', 'id συναλλαγών', 'Transaktions-ID', 'id transazione', 'รายการ ID', 'ٹرانزیکشن کی شناخت', 'लेन - देन आईडी', 'id negotii', 'id transaksi', 'トランザクションID', '트랜잭션 ID'),
(104, 'invoice_id', 'invoice id', 'চালান আইডি', 'Identificación factura', 'رقم الفاتورة', 'factuur id', 'счет-фактура ID', '发票编号', 'fatura id', 'id fatura', 'számla id', 'Identifiant facture', 'τιμολογίου id', 'Rechnung id', 'id fattura', 'id ของใบแจ้งหนี้', 'انوائس کی شناخت', 'चालान आईडी', 'ID cautionem', 'id faktur', '請求書のID', '송장 번호'),
(105, 'method', 'method', 'পদ্ধতি', 'método', 'طريقة', 'methode', 'метод', '方法', 'yöntem', 'método', 'módszer', 'méthode', 'μέθοδος', 'Verfahren', 'Metodo', 'วิธี', 'طریقہ', 'विधि', 'methodo', 'metode', '方法', '방법'),
(106, 'bed_list', 'bed list', 'বিছানা তালিকা', 'Lista de cama', 'قائمة سرير', 'bed lijst', 'кровать список', '床列表', 'yatak liste', 'Lista de cama', 'ágy lista', 'liste de lit', 'Λίστα κρεβάτι', 'Bett Liste', 'Lista letto', 'รายการเตียง', 'بستر کی فہرست', 'बिस्तर सूची', 'stratum album', 'Daftar tidur', 'ベッド一覧', '침대 목록'),
(107, 'bed_id', 'bed id', 'বিছানা আইডি', 'Identificación cama', 'معرف السرير', 'bed id', 'кровать ID', '床上ID', 'yatak id', 'id cama', 'ágy id', 'Identifiant du lit', 'id κρεβάτι', 'Bett-ID', 'id letto', 'ID เตียง', 'بستر کی شناخت', 'बिस्तर आईडी', 'ID lectulo', 'tidur id', 'ベッド番号', '침대 ID'),
(108, 'bed_type', 'bed type', 'বিছানা টাইপ', 'tipo de cama', 'نوع السرير', 'Type bed', 'тип кровати', '床型', 'yatak tipi', 'tipo de cama', 'szoba típus', 'Type de lit', 'τύπο κρεβατιού', 'Bett-Typ', 'Tipo di letto', 'ประเภทเตียง', 'بستر کی قسم', 'बिस्तर प्रकार', 'stratum typus', 'jenis tempat tidur', 'ベッドタイプ', '침대 유형'),
(109, 'allotment_time', 'allotment time', 'বন্টনের সময়', 'asignación del tiempo', 'وقت التخصيص', 'allotment tijd', 'выделение времени', '配发时间', 'tahsis süresi', 'tempo loteamento', 'elosztás idő', 'temps d\'attribution', 'κατανομή του χρόνου', 'Zuteilung Zeit', 'tempo di riparto', 'เวลาจัดสรร', 'الاٹمنٹ کے وقت', 'आवंटन के समय', 'sortitio tempore', 'waktu penjatahan', '割当時間', '할당 시간'),
(110, 'discharge_time', 'discharge time', 'স্রাব সময়', 'tiempo de descarga', 'وقت التفريغ', 'ontlaadtijd', 'Время разряда', '放电时间', 'boşalma süresi', 'tempo de descarga', 'kisütési idő', 'temps de décharge', 'χρόνο εκροής', 'Entladezeit', 'tempo di scarica', 'เวลาปลดประจำการ', 'خارج ہونے والے مادہ کا وقت', 'छुट्टी के समय', 'tempus fluit', 'waktu pengosongan', '放電時間', '방전 시간'),
(111, 'bed_number', 'bed number', 'বেড সংখ্যা', 'número de la cama', 'عدد السرير', 'bed nummer', 'номер кровать', '床号', 'yatak sayısı', 'número cama', 'ágyszám', 'Numéro du lit', 'αριθμό κλινών', 'Bettnummer', 'numero letto', 'จำนวนเตียง', 'بیڈ نمبر', 'बिस्तर संख्या', 'torum numero', 'nomor tidur', 'ベッド数', '침대 수'),
(112, 'type', 'type', 'টাইপ', 'tipo', 'نوع', 'type', 'тип', '类型', 'tip', 'tipo', 'típus', 'catégorie', 'τύπος', 'Art', 'Tipo di', 'ชนิด', 'قسم', 'टाइप', 'type', 'jenis', 'タイプ', '유형'),
(113, 'blood_donor_list', 'blood donor list', 'রক্ত দাতার তালিকা', 'lista de donantes de sangre', 'قائمة متبرعين بالدم', 'bloeddonor lijst', 'Список доноров крови', '献血者名单', 'kan bağış yapanlar listesinde', 'lista de doadores de sangue', 'véradó lista', 'liste des donneurs de sang', 'αίμα λίστα των χορηγών', 'Blutspender Liste', 'Lista dei donatori di sangue', 'รายชื่อผู้บริจาคเลือด', 'خون کے عطیہ دہندگان کی فہرست', 'रक्त दाता सूची', 'sanguinem donator album', 'Daftar donor darah', '血液ドナーリスト', '혈액 기증자 목록'),
(114, 'last_donation_date', 'last donation date', 'শেষ দান জন্ম', 'última fecha de la donación', 'مشاركة تاريخ التبرع', 'laatste donatie datum', 'Дата последнего пожертвования', '最后捐赠日期', 'son bağış tarih', 'data da última doação', 'utolsó adomány dátum', 'date du dernier don', 'τελευταία ημερομηνία δωρεά', 'letzten Spende Datum', 'ultima data di donazione', 'วันที่บริจาคล่าสุด', 'گزشتہ عطیہ تاریخ', 'पिछले दान तारीख', 'novissimo tempore donationis', 'Tanggal sumbangan lalu', '最後の寄付日', '마지막으로 기부 날짜'),
(115, 'status', 'status', 'অবস্থা', 'estado', 'حالة', 'toestand', 'статус', '状态', 'durum', 'estado', 'állapot', 'statut', 'κατάσταση', 'Status', 'stato', 'สถานะ', 'درجہ', 'हैसियत', 'Status', 'status', 'ステータス', '지위'),
(116, 'category', 'category', 'বিভাগ', 'categoría', 'فئة', 'categorie', 'категория', '类别', 'kategori', 'categoria', 'kategória', 'catégorie', 'κατηγορία', 'Kategorie', 'categoria', 'หมวดหมู่', 'زمرے', 'श्रेणी', 'praedicamenti', 'kategori', 'カテゴリ', '범주'),
(117, 'price', 'price', 'দাম', 'precio', 'السعر', 'prijs', 'цена', '价格', 'fiyat', 'preço', 'ár', 'prix', 'τιμή', 'Preis', 'prezzo', 'ราคา', 'قیمت', 'कीमत', 'pretium', 'harga', '価格', '가격'),
(118, 'manufacturing_company', 'manufacturing company', 'উত্পাদন কোম্পানি', 'empresa de fabricación', 'شركة التصنيع', 'productiebedrijf', 'производственная фирма', '制造商', 'üretim şirketi', 'empresa de fabricação', 'gyártó cég', 'entreprise de fabrication', 'κατασκευαστική εταιρεία', 'Produktionsunternehmen', 'società di produzione', 'บริษัท ผู้ผลิต', 'مینوفیکچرنگ کمپنی', 'निर्माण कंपनी', 'vestibulum consortia', 'perusahaan manufaktur', '製造会社', '제조 회사'),
(119, 'view_operation_report', 'view operation report', 'অপারেশন রিপোর্ট', 'ver la confirmación de la operación', 'عرض تقرير العملية', 'bekijk bediening rapport', 'просмотреть отчет о работе', '查看操作报告', 'çalışma raporunu görüntülemek', 'exibir o relatório de operação', 'megtekintéséhez működéséről szóló jelentés', 'voir rapport d\'opération', 'δείτε αναφορά λειτουργίας', 'OP-Bericht ansehen', 'visualizzare il report di funzionamento', 'ดูรายงานการดำเนินงาน', 'آپریشن کی رپورٹ دیکھنے کے', 'ऑपरेशन रिपोर्ट को देखने', 'considerabit operatio auditui', 'melihat laporan operasi', '操作レポートを表示', '운영 보고서를 보려면'),
(120, 'view_report', 'view report', 'রিপোর্ট', 'ver el informe', 'عرض تقرير', 'rapport bekijken', 'Просмотреть отчет', '查看报告', 'Raporu görüntülemek', 'ver relatório', 'jelentés megtekintése', 'voir rapport', 'προβάλετε την αναφορά', 'Bericht ansehen', 'visualizzare il report', 'ดูรายงาน', 'رپورٹ دیکھنے کے', 'रिपोर्ट देखने', 'considerabit auditui', 'melihat laporan', 'レポートを見る', '보고서를 보려면'),
(121, 'date', 'date', 'জন্ম', 'fecha', 'تاريخ', 'datum', 'дата', '日', 'tarih', 'data', 'dátum', 'Date', 'ημερομηνία', 'Datum', 'Data', 'วันที่', 'تاریخ', 'तारीख', 'date', 'tanggal', '日付', '날짜'),
(122, 'noticeboard_list', 'noticeboard list', 'নোটিশবোর্ড তালিকা', 'Lista tablón de anuncios', 'قائمة اللافتة', 'prikbord lijst', 'доске объявлений список', '布告名单', 'panosuna liste', 'lista de avisos', 'üzenőfalán lista', 'liste de tableau d\'affichage', 'Λίστα Ανακοινώσεων', 'Brett Liste', 'Lista bacheca', 'รายการ noticeboard', 'noticeboard فہرست', 'नोटिसबोर्ड सूची', 'noticeboard album', 'daftar pengumuman', '掲示板一覧', '공지 사항 more 목록'),
(123, 'add_noticeboard', 'add noticeboard', 'নোটিশবোর্ড যোগ করুন', 'añadir tablón de anuncios', 'إضافة اللافتة', 'voegen prikbord', 'добавить доску объявлений', '加布告', 'panosuna ekleyin', 'adicionar avisos', 'hozzá üzenőfalán', 'ajouter tableau d\'affichage', 'προσθήκη Ανακοινώσεων', 'hinzufügen noticeboard', 'aggiungere bacheca', 'เพิ่ม noticeboard', 'noticeboard شامل', 'नोटिसबोर्ड जोड़', 'adde noticeboard', 'tambahkan pengumuman', '掲示板を追加', '공지 사항 more를 추가'),
(124, 'title', 'title', 'শিরোনাম', 'título', 'لقب', 'titel', 'название', '标题', 'başlık', 'título', 'cím', 'titre', 'τίτλος', 'Titel', 'titolo', 'ชื่อเรื่อง', 'عنوان', 'शीर्षक', 'title', 'judul', 'タイトル', '이름'),
(125, 'notice', 'notice', 'বিজ্ঞপ্তি', 'notar', 'إشعار', 'opmerken', 'замечать', '注意', 'dikkat', 'notar', 'észrevesz', 'remarquer', 'ανακοίνωση', 'Bekanntmachung', 'preavviso', 'สังเกตเห็น', 'نوٹس', 'नोटिस', 'Observa', 'memperhatikan', '気付く', '통지'),
(126, 'system_name', 'system name', 'সিস্টেমের নামের', 'Nombre del sistema', 'اسم النظام', 'Name System', 'имя системы', '系统名称', 'sistemi adı', 'nome do sistema', 'rendszer neve', 'nom de système', 'όνομα του συστήματος', 'System Name', 'nome del sistema', 'ชื่อระบบ', 'نظام کا نام', 'सिस्टम नाम', 'nomen ratio', 'nama sistem', 'システム名', '시스템 이름'),
(127, 'save', 'save', 'সংরক্ষণ করুন', 'guardar', 'حفظ', 'sparen', 'экономить', '节省', 'kurtarmak', 'salvar', 'kivéve', 'sauver', 'εκτός', 'speichern', 'salva', 'ประหยัด', 'کو بچانے کے', 'बचाना', 'Salvum', 'menyimpan', '保存', '저장'),
(128, 'system_email', 'system email', 'সিস্টেম ইমেল', 'sistema de correo electrónico', 'نظام البريد الإلكتروني', 'systeem e-mail', 'системы электронной почты', '电子邮件系统', 'sistemi e-posta', 'sistema de e-mail', 'rendszer email', 'email de système', 'σύστημα ηλεκτρονικού ταχυδρομείου', 'E-Mail-System', 'e-mail del sistema', 'ระบบอีเมล', 'نظام کی ای میل', 'प्रणाली ईमेल', 'email ratio', 'email sistem', 'システムのメール', '시스템 전자 메일'),
(129, 'system_title', 'system title', 'সিস্টেম শিরোনাম', 'Título sistema', 'عناوين النظام', 'systeem titel', 'Система название', '系统标题', 'Sistem başlık', 'título sistema', 'rendszer cím', 'titre du système', 'Τίτλος του συστήματος', 'System Titel', 'del titolo di sistema', 'ชื่อระบบ', 'نظام عنوان', 'सिस्टम शीर्षक', 'ratio title', 'judul sistem', 'システムのタイトル', '시스템 제목'),
(130, 'paypal_email', 'paypal email', 'PayPal এর ইমেইল', 'email paypal', 'البريد الإلكتروني باي بال', 'paypal e-mail', 'PayPal e-mail', 'PayPal电子邮件', 'paypal e-posta', 'email paypal', 'paypal email', 'email paypal', 'paypal email', 'paypal E-Mail', 'email paypal', 'อีเมล์ PayPal', 'پے پال ای میل', 'पेपैल ईमेल', 'Paypal email', 'email paypal', 'ペイパルメール', '페이팔 이메일'),
(131, 'currency', 'currency', 'মুদ্রা', 'moneda', 'عملة', 'valuta', 'валюта', '货币', 'para', 'moeda', 'valuta', 'monnaie', 'νόμισμα', 'Währung', 'valuta', 'เงินตรา', 'کرنسی', 'मुद्रा', 'monetæ', 'mata uang', '通貨', '통화'),
(132, 'restore', 'restore', 'প্রত্যর্পণ করা', 'restaurar', 'استعادة', 'herstellen', 'восстановление', '恢复', 'geri', 'restaurar', 'visszaad', 'rétablir', 'αποκατάσταση', 'wiederherstellen', 'ripristinare', 'ฟื้นฟู', 'بحال', 'बहाल', 'restituo', 'memulihkan', '復元する', '복원'),
(133, 'report', 'report', 'প্রতিবেদন', 'reportar', 'تقرير', 'rapporteren', 'сообщить', '报告', 'rapor', 'denunciar', 'jelentés', 'signaler', 'έκθεση', 'berichten', 'segnalare', 'แจ้ง', 'رپورٹ', 'रिपोर्ट', 'referrent', 'melaporkan', 'レポート', '보고'),
(134, 'all', 'all', 'সমস্ত', 'todo', 'كل', 'alle', 'все', '所有', 'tüm', 'tudo', 'minden', 'tous', 'όλα', 'alle', 'tutti', 'ทั้งหมด', 'تمام', 'सब', 'omnes', 'semua', 'すべて', '모든'),
(135, 'upload_&_restore_from_backup', 'upload & restore from backup', 'আপলোড & ব্যাকআপ থেকে পুনঃস্থাপন', 'cargar y restaurar la copia de seguridad', 'تحميل واستعادة من النسخة الاحتياطية', 'uploaden en terugzetten van een backup', 'загрузить и восстановить из резервной копии', '上传及从备份中还原', 'yüklemek ve yedekten geri yükleme', 'enviar e restaurar a partir de backup', 'feltölteni és visszaállítani backup', 'télécharger et de restauration de la sauvegarde', 'ανεβάσετε και επαναφορά από backup', 'Upload und Wiederherstellung von Backups', 'caricare e ripristinare dal backup', 'อัพโหลดและเรียกคืนจากการสำรองข้อมูล', 'اپ لوڈ اور بیک اپ سے بحال', 'अपलोड और बैकअप से बहाल', '&amp; restituo ex tergum upload', 'meng-upload &amp; restore dari backup', 'アップロード＆バックアップから復元', '업로드 및 백업에서 복원'),
(136, 'manage_profile', 'manage profile', 'প্রফাইল পরিচালনা', 'gestionar el perfil', 'إدارة الملف الشخصي', 'beheren profiel', 'Управление профилем', '管理配置文件', 'profilinizi', 'gerenciar o perfil', 'Profil kezelése', 'Gérer le profil', 'διαχειριστείτε το προφίλ', 'Profil verwalten', 'gestire il profilo', 'จัดการรายละเอียด', 'پروفائل کا انتظام', 'प्रोफ़ाइल का प्रबंधन', 'aget profile', 'mengelola profil', 'プロファイルの管理', '프로필 (내 정보) 관리'),
(137, 'update_profile', 'update profile', 'প্রোফাইল আপডেট', 'actualizar el perfil', 'تحديث الملف الشخصي', 'updaten profiel', 'обновления профиля', '更新个人资料', 'Profil güncelleme', 'atualizar o perfil', 'Profil frissítése', 'mettre à jour le profil', 'ενημερώσετε το προφίλ', 'Profil aktualisieren', 'aggiornare il profilo', 'ปรับปรุงรายละเอียด', 'پروفائل کی تازہ کاری', 'प्रोफ़ाइल का अद्यतन', 'update profile', 'memperbarui profil', 'プロファイルを更新', '프로필을 업데이트'),
(138, 'change_password', 'change password', 'পাসওয়ার্ড পরিবর্তন করুন', 'cambiar la contraseña', 'تغيير كلمة المرور', 'wachtwoord wijzigen', 'сменить пароль', '更改密码', 'şifresini değiştirmek', 'alterar a senha', 'jelszó megváltoztatása', 'changer le mot de passe', 'αλλάξετε τον κωδικό πρόσβασης', 'Kennwort ändern', 'modificare la password', 'เปลี่ยนรหัสผ่าน', 'پاس ورڈ تبدیل کریں', 'पासवर्ड बदलना', 'mutare password', 'mengubah password', 'パスワードを変更する', '암호를 변경'),
(139, 'new_password', 'new password', 'নতুন পাসওয়ার্ড', 'nueva contraseña', 'كلمة مرور جديدة', 'nieuw wachtwoord', 'новый пароль', '新密码', 'Yeni şifre', 'nova senha', 'Új jelszó', 'nouveau mot de passe', 'νέο κωδικό', 'Neues Passwort', 'nuova password', 'รหัสผ่านใหม่', 'نیا پاس ورڈ', 'नया पासवर्ड', 'Novum password', 'sandi baru', '新しいパスワード', '새 암호를'),
(140, 'confirm_new_password', 'confirm new password', 'নতুন পাসওয়ার্ড নিশ্চিত করুন', 'confirmar nueva contraseña', 'تأكيد كلمة المرور الجديدة', 'Bevestig nieuw wachtwoord', 'Подтвердите новый пароль', '确认新密码', 'yeni parolayı onaylayın', 'confirmar nova senha', 'erősítse meg az új jelszót', 'confirmer le nouveau mot de passe', 'επιβεβαιώσετε το νέο κωδικό', 'Bestätigen eines neuen Kennwortes', 'confermare la nuova password', 'ยืนยันรหัสผ่านใหม่', 'نئے پاس ورڈ کی توثیق', 'नए पासवर्ड की पुष्टि', 'Confirmabit novum Signum', 'konfirmasi password baru', '新しいパスワードを確認', '새 암호를 확인합니다'),
(141, 'update_password', 'update password', 'হালনাগাদ পাসওয়ার্ড', 'actualización de la contraseña', 'تحديث كلمة السر', 'Update wachtwoord', 'Обновление пароля', '更新密码', 'güncelleştirme şifre', 'atualização de senha', 'frissítési jelszó', 'mise à jour le mot de passe', 'ενημερώσετε τον κωδικό πρόσβασης', 'Update vergessen', 'aggiornamento della password', 'รหัสผ่านการปรับปรุง', 'اپ ڈیٹ پاس ورڈ', 'अद्यतन पासवर्ड', 'update Signum', 'Update sandi', 'パスワードを更新', '업데이트 암호');
INSERT INTO `language` (`phrase_id`, `phrase`, `english`, `bengali`, `spanish`, `arabic`, `dutch`, `russian`, `chinese`, `turkish`, `portuguese`, `hungarian`, `french`, `greek`, `german`, `italian`, `thai`, `urdu`, `hindi`, `latin`, `indonesian`, `japanese`, `korean`) VALUES
(142, 'option', 'option', 'বিকল্প', 'opción', 'خيار', 'optie', 'вариант', '选项', 'seçenek', 'opção', 'opció', 'choix', 'επιλογή', 'Wahl', 'opzione', 'การเลือก', 'آپشن', 'विकल्प', 'optionem', 'opsi', 'オプション', '선택권'),
(143, 'edit_phrase', 'edit phrase', 'ফ্রেজ সম্পাদনা', 'editar frase', 'تحرير العبارة', 'bewerk zin', 'редактировать фразу', '编辑短语', 'ifade düzenlemek', 'editar frase', 'szerkesztés kifejezés', 'éditer phrase', 'επεξεργαστείτε φράση', 'bearbeiten Phrase', 'modificare frase', 'แก้ไขวลี', 'جملہ ترمیم کریں', 'वाक्यांश को संपादित', 'recensere phrase', 'mengedit frase', 'フレーズを編集', '문구를 편집'),
(144, 'edit_noticeboard', 'edit noticeboard', 'নোটিশবোর্ড সম্পাদনা', 'editar tablón de anuncios', 'تعديل اللافتة', 'bewerk prikbord', 'редактировать доске объявлений', '编辑布告', 'panosuna düzenleme', 'editar avisos', 'szerkesztés hirdetőtábla', 'éditer panneau d\'', 'επεξεργαστείτε Ανακοινώσεων', 'bearbeiten noticeboard', 'modificare bacheca', 'แก้ไข noticeboard', 'noticeboard ترمیم کریں', 'नोटिसबोर्ड संपादित', 'recensere noticeboard', 'mengedit pengumuman', '掲示板を編集', '공지 사항 more 편집'),
(145, 'doctor_dashboard', 'doctor dashboard', 'ডাক্তার ড্যাশবোর্ড', 'médico salpicadero', 'طبيب لوحة أجهزة القياس', 'arts dashboard', 'Врач приборной панели', '医生仪表板', 'doktor paneli', 'médico dashboard', 'orvos műszerfal', 'médecin tableau de bord', 'Ο γιατρός ταμπλό', 'Arzt Armaturenbrett', 'medico cruscotto', 'แผงควบคุมของแพทย์', 'ڈاکٹر ڈیش بورڈ', 'डॉक्टर डैशबोर्ड', 'Medicus Dashboard', 'Dokter dashboard', '医師ダッシュ', '의사 대시 보드'),
(146, 'manage_appointment', 'manage appointment', 'অ্যাপয়েন্টমেন্ট পরিচালনা', 'gestionar cita', 'تعيين إدارة', 'beheren afspraak', 'управлять назначением', '管理预约', 'randevu yönetmek', 'gerenciar nomeação', 'kezelni találkozó', 'gérer les rendez-vous', 'διαχείριση ραντεβού', 'Termin verwalten', 'gestire appuntamento', 'จัดการแต่งตั้ง', 'تقرری کا انتظام', 'नियुक्ति का प्रबंधन', 'gere institutionis', 'mengelola janji', 'アポイントメントを管理', '약속을 관리'),
(147, 'manage_prescription', 'manage prescription', 'প্রেসক্রিপশন পরিচালনা', 'gestión de la prescripción', 'إدارة وصفة طبية', 'beheren recept', 'управлять рецепту', '处方管理', 'reçete yönetmek', 'gerenciar prescrição', 'kezelni recept', 'gérer prescription', 'διαχείριση συνταγή', 'verwalten Rezept', 'gestire prescrizione', 'บริหารจัดการตามใบสั่งแพทย์', 'نسخے کا انتظام', 'पर्चे का प्रबंधन', 'praescriptio curo', 'mengelola resep', '処方箋を管理', '처방 관리'),
(148, 'manage_report', 'manage report', 'রিপোর্ট পরিচালনা', 'gestionar informe', 'إدارة تقرير', 'rapport beheren', 'управлять отчета', '管理报告', 'rapor yönetmek', 'gerenciar relatório', 'kezelni jelentés', 'gérer rapport', 'διαχειρίζονται την έκθεση', 'verwalten Bericht', 'gestione rapporto', 'จัดการรายงาน', 'رپورٹ کا انتظام', 'रिपोर्ट का प्रबंधन', 'fama curo', 'mengelola laporan', 'レポートの管理', '보고서 관리'),
(149, 'prescription', 'prescription', 'প্রেসক্রিপশন', 'prescripción', 'وصفة طبية', 'recept', 'рецепт', '处方', 'reçete', 'prescrição', 'recept', 'ordonnance', 'συνταγή', 'Verschreibung', 'prescrizione', 'ใบสั่งยา', 'نسخے', 'नुस्खा', 'praescriptio', 'resep', '処方箋', '처방'),
(150, 'edit_patient', 'edit patient', 'রোগীর সম্পাদনা', 'editar paciente', 'تحرير المريض', 'bewerk patiënt', 'редактировать пациента', '编辑患者', 'Hasta düzenleme', 'editar paciente', 'szerkesztés beteg', 'éditer des patients', 'επεξεργαστείτε ασθενή', 'Bearbeitung von Patientendaten', 'modificare paziente', 'แก้ไขผู้ป่วย', 'مریض میں ترمیم کریں', 'रोगी को संपादित', 'recensere patientes', 'mengedit pasien', '患者を編集', '환자를 편집'),
(151, 'appointment_list', 'appointment list', 'অ্যাপয়েন্টমেন্ট তালিকা', 'lista de citas', 'قائمة التعيين', 'afsprakenlijst', 'Назначение списка', '任命名单', 'randevu listesi', 'lista de compromissos', 'kinevezés lista', 'liste des rendez-vous', 'κατάλογο διοριστέων', 'Terminliste', 'Lista appuntamento', 'รายการได้รับการแต่งตั้ง', 'تقرری کی فہرست', 'नियुक्ति सूची', 'Nominatio album', 'Daftar janji', 'アポイントメントリスト', '약속 목록'),
(152, 'add_appointment', 'add appointment', 'অ্যাপয়েন্টমেন্ট যোগ করুন', 'añadir cita', 'إضافة موعد', 'voegen afspraak', 'добавить назначения', '加预约', 'randevu eklemek', 'adicionar nomeação', 'hozzá találkozó', 'ajouter rendez-vous', 'προσθέστε το διορισμό', 'neuen Termin', 'aggiungere appuntamento', 'เพิ่มการแต่งตั้ง', 'تقرری شامل', 'नियुक्ति जोड़', 'adde constitutio', 'menambahkan penunjukan', '予定を追加', '약속 추가'),
(153, 'edit_appointment', 'edit appointment', 'অ্যাপয়েন্টমেন্ট সম্পাদনা', 'editar nombramiento', 'تعديل موعد', 'bewerk afspraak', 'редактировать назначения', '编辑预约', 'randevu düzenleme', 'editar nomeação', 'szerkesztés találkozó', 'modifier les rendez-vous', 'επεξεργαστείτε ραντεβού', 'Termin bearbeiten', 'modificare appuntamento', 'แก้ไขการแต่งตั้ง', 'تقرری میں ترمیم کریں', 'नियुक्ति संपादित', 'recensere constitutio', 'mengedit janji', '任命を編集', '약속을 편집합니다'),
(154, 'prescription_list', 'prescription list', 'প্রেসক্রিপশন তালিকা', 'Lista de prescripción', 'قائمة وصفة طبية', 'recept lijst', 'рецепт список', '药物名单', 'reçete listesi', 'Lista de prescrição', 'recept lista', 'liste prescription', 'συνταγή λίστα', 'Rezept Liste', 'Lista prescrizione', 'รายการใบสั่งยา', 'نسخے کی فہرست', 'पर्चे की सूची', 'praescriptio album', 'Daftar resep', '処方リスト', '처방전 목록'),
(155, 'add_prescription', 'add prescription', 'প্রেসক্রিপশন যোগ করুন', 'añadir receta', 'إضافة وصفة طبية', 'add recept', 'добавить рецепт', '将处方', 'reçete ekleyin', 'adicionar prescrição', 'hozzá recept', 'ajouter prescription', 'προσθήκη συνταγή', 'Rezept hinzufügen', 'aggiungere prescrizione', 'เพิ่มตามใบสั่งแพทย์', 'نسخے شامل', 'पर्चे जोड़', 'adde praescriptione', 'menambahkan resep', '処方箋を追加', '처방전을 추가'),
(156, 'case_history', 'case history', 'রোগীর স্বাস্থ্যাদির বিবরণ', 'historia clínica', 'تاريخ الحالة', 'anamnese', 'история болезни', '病历', 'geçmiş', 'história do caso', 'kórtörténet', 'anamnèse', 'ιατρικό ιστορικό ασθενούς', 'Anamnese', 'case history', 'ประวัติผู้ป่วย', 'کیس کی تاریخ', 'पूर्ववृत्त', 'ita history', 'hal ihwal', '病歴', '병력'),
(157, 'add_description', 'add description', 'বিবরণ যুক্ত করুন', 'añadir una descripción', 'إضافة الوصف', 'add omschrijving', 'добавить описание', '添加描述', 'Açıklama eklemek', 'adicionar uma descrição', 'Leírás hozzáadása', 'ajouter une description', 'προσθέσετε την περιγραφή', 'Beschreibung hinzufügen', 'aggiungere descrizione', 'เพิ่มรายละเอียด', 'تفصیل شامل', 'विवरण जोड़ें', 'adde descriptionem', 'menambahkan deskripsi', '説明を追加', '설명을 추가'),
(158, 'medication', 'medication', 'চিকিত্সা', 'medicación', 'دواء', 'geneesmiddel', 'лекарство', '药物治疗', 'ilaç', 'medicação', 'gyógyszer', 'médication', 'φάρμακο', 'Behandlung', 'medicazione', 'การรักษาด้วยยา', 'ادویات', 'इलाज', 'medication', 'obat', '投薬', '약물 치료'),
(159, 'medication_from_pharmacist', 'medication from pharmacist', 'ফার্মাসিস্টের কাছ থেকে ঔষধ', 'medicamento del farmacéutico', 'الدواء من الصيدلي', 'medicatie van apotheker', 'лекарство от фармацевта', '从药师用药', 'eczacıdan ilaç', 'medicação de farmacêutico', 'gyógyszert gyógyszerész', 'médicaments de pharmacien', 'φάρμακα από τον φαρμακοποιό', 'Medikamente von Apotheker', 'farmaci da farmacista', 'ยาจากเภสัชกร', 'فارماسسٹ سے ادویات', 'फार्मासिस्ट से दवा', 'medication a pharmacopola', 'obat dari apoteker', '薬剤師から薬', '약사의 약물 치료'),
(160, 'edit_prescription', 'edit prescription', 'প্রেসক্রিপশন সম্পাদনা', 'editar prescripción', 'تحرير وصفة طبية', 'bewerk recept', 'редактировать рецепту', '编辑处方', 'reçete düzenlemek', 'editar prescrição', 'szerkesztés recept', 'modifier prescription', 'επεξεργαστείτε συνταγή', 'bearbeiten Rezept', 'modificare prescrizione', 'แก้ไขใบสั่งยา', 'نسخے میں ترمیم کریں', 'पर्चे संपादित', 'recensere praescriptione', 'mengedit resep', '処方箋を編集', '처방전을 편집합니다'),
(161, 'diagnosis_report', 'diagnosis report', 'রোগ নির্ণয়ের রিপোর্ট', 'informe de diagnóstico', 'تقرير التشخيص', 'diagnose rapport', 'Диагноз отчет', '诊断报告', 'tanı raporu', 'relatório de diagnóstico', 'diagnózis jelentés', 'rapport de diagnostic', 'Έκθεση διάγνωση', 'Diagnose Bericht', 'rapporto di diagnosi', 'รายงานการวินิจฉัย', 'تشخیص کی رپورٹ', 'निदान रिपोर्ट', 'fama diagnosis', 'Laporan diagnosis', '診断レポート', '진단 보고서'),
(162, 'report_type', 'report type', 'টাইপ প্রতিবেদন', 'informar de tipo', 'نوع التقرير', 'Type melden', 'Тип отчета', '报告类型', 'türlü rapor', 'denunciar o tipo', 'Jelentés típusa', 'Type de rapport', 'Αναφορά Ειδών', 'berichten Art', 'Tipo di rapporto', 'แจ้งประเภท', 'قسم کی رپورٹ', 'प्रकार रिपोर्ट', 'type referrent', 'melaporkan jenis', 'レポートの種類', '유형을보고'),
(163, 'document_type', 'document type', 'নথির ধরণ', 'tipo de documento', 'نوع الوثيقة', 'documenttype', 'тип документа', '文件类型', 'belge türü', 'tipo de documento', 'dokumentum típusa', 'type de document', 'Τύπος εγγράφου', 'Belegart', 'tipo di documento', 'ประเภทของเอกสาร', 'دستاویز کی قسم', 'दस्तावेज़ प्रकार', 'genus scripti', 'Jenis dokumen', 'ドキュメントタイプ', '문서 유형'),
(164, 'download', 'download', 'ডাউনলোড', 'descargar', 'تحميل', 'downloaden', 'скачать', '下载', 'indir', 'baixar', 'letöltés', 'télécharger', 'κατεβάστε', 'downloaden', 'caricare', 'ดาวน์โหลด', 'ڈاؤن لوڈ، اتارنا', 'डाउनलोड', 'download', 'mendownload', 'ダウンロード', '다운로드'),
(165, 'manage_bed_allotment', 'manage bed allotment', 'বিছানা অ্যালটমেন্ট পরিচালনা', 'gestionar adjudicación cama', 'إدارة المخصصات السرير', 'beheren bed toewijzing', 'управлять кроватью выделение', '管理床配发', 'yatak tahsis yönetmek', 'gerenciar cama loteamento', 'kezelni ágy elosztás', 'gérer l\'attribution de lit', 'διαχείριση κατανομής κρεβάτι', 'verwalten Bett Zuteilung', 'gestire letto riparto', 'บริหารจัดการการจัดสรรเตียง', 'بستر الاٹمنٹ کا انتظام', 'बिस्तर आवंटन का प्रबंधन', 'partem stratum curo', 'mengelola tidur penjatahan', 'ベッドの割当てを管理する', '침대 부담금 관리'),
(166, 'bed_allotment_list', 'bed allotment list', 'বিছানা আবণ্টন তালিকা', 'lista de adjudicación cama', 'قائمة تخصيص سرير', 'bed toewijzing lijst', 'Список кровать выделение', '床上配发清单', 'yatak tahsis liste', 'lista loteamento cama', 'ágy elosztás lista', 'liste d\'attribution de lit', 'κρεβάτι λίστα κατανομής', 'Bett Zuteilung Liste', 'Lista riparto letto', 'รายการจัดสรรเตียง', 'بستر الاٹمنٹ کی فہرست', 'बिस्तर आवंटन सूची', 'partem stratum album', 'daftar tempat tidur penjatahan', 'ベッド割当一覧', '침대 할당 목록'),
(167, 'add_bed_allotment', 'add bed allotment', 'বিছানা অ্যালটমেন্ট যোগ করুন', 'añadir adjudicación cama', 'إضافة تخصيص سرير', 'add bed toewijzing', 'добавить кровать выделение', '加床上配发', 'yatak tahsis ekleyin', 'adicionar cama loteamento', 'hozzá ágy elosztás', 'ajouter attribution de lit', 'προσθήκη κατανομής κρεβάτι', 'hinzufügen Bett Zuteilung', 'aggiungere letto riparto', 'เพิ่มการจัดสรรเตียง', 'بستر الاٹمنٹ شامل', 'बिस्तर आवंटन जोड़', 'adde lectum sorti', 'tambahkan tidur penjatahan', 'ベッド割当てを追加', '침대 할당을 추가'),
(168, 'allotment_date_time', 'allotment date time', 'অ্যালটমেন্ট তারিখ সময়', 'Fecha y hora de adjudicación', 'تخصيص وقت التسجيل', 'toewijzing datum tijd', 'Время выделения дате', '配发日期时间', 'tahsis tarih saat', 'atribuição de data e hora', 'elosztás dátum időpont', 'lotissement date heure', 'κατανομή του χρόνου ημερομηνία', 'Zuteilung Datum Zeit', 'riparto data ora', 'วันที่เวลาจัดสรร', 'الاٹمنٹ تاریخ وقت', 'आवंटन की तारीख समय', 'sortitio date tempore', 'penjatahan waktu tanggal', '割当日の時間', '부담금 날짜 시간'),
(169, 'discharge_date_time', 'discharge date time', 'স্রাব তারিখ সময়', 'Fecha y hora de descarga', 'تصريف الوقت التسجيل', 'ontslagdatum tijd', 'время разряда дате', '出院日期时间', 'deşarj tarih saat', 'descarga de data e hora', 'kisülési dátum időpont', 'temps de décharge de la date', 'Απαλλαγή ημερομηνίας και ώρας', 'Entlastung Datum Zeit', 'tempo di scarico data', 'เวลาวันที่ปลดประจำการ', 'خارج ہونے والے مادہ تاریخ وقت', 'मुक्ति दिनांक समय', 'date tempore missionem', 'discharge waktu tanggal', '放電日時', '방전 된 날짜와 시간'),
(170, 'operation', 'operation', 'অপারেশন', 'operación', 'عملية', 'operatie', 'операция', '手术', 'operasyon', 'operação', 'működés', 'opération', 'λειτουργία', 'Betrieb', 'operazione', 'การทำงาน', 'آپریشن', 'आपरेशन', 'Operatio', 'operasi', '操作', '운전'),
(171, 'birth', 'birth', 'আমার', 'nacimiento', 'الولادة', 'geboorte', 'рождение', '分娩', 'doğum', 'nascimento', 'szülés', 'naissance', 'γέννηση', 'Geburt', 'nascita', 'กำเนิด', 'پیدائش', 'जन्म', 'Nativitas', 'kelahiran', '出産', '출생'),
(172, 'death', 'death', 'মৃতু্য', 'muerte', 'الموت', 'dood', 'смерть', '死亡', 'ölüm', 'morte', 'halál', 'décès', 'θάνατος', 'Sterben', 'scomparsa', 'ความตาย', 'موت', 'मौत', 'mortem', 'kematian', '死', '죽음'),
(173, 'other', 'other', 'অন্য', 'otro', 'آخر', 'ander', 'другой', '其他', 'diğer', 'outro', 'más', 'autre', 'άλλος', 'andere', 'altro', 'อื่น ๆ', 'دیگر', 'अन्य', 'aliud', 'lain', 'その他', '다른'),
(174, 'add_report', 'add report', 'প্রতিবেদনে যোগ', 'agregar informe', 'إضافة تقرير', 'verslag toe te voegen', 'Добавить отчет', '加报告', 'rapor ekleyin', 'adicionar relatório', 'hozzá jelentés', 'Ajouter un rapport', 'προσθέστε την έκθεση', 'Bericht hinzufügen', 'aggiungere rapporto', 'เพิ่มรายงาน', 'رپورٹ میں شامل', 'रिपोर्ट जोड़', 'adde auditui', 'menambahkan laporan', 'レポートを追加', '보고서를 추가'),
(175, 'patient_dashboard', 'patient dashboard', 'রোগীর ড্যাশবোর্ড', 'tablero paciente', 'لوحة أجهزة القياس المريض', 'patiënt dashboard', 'Пациент приборной панели', '病人的仪表板', 'Hasta tablosu', 'dashboard paciente', 'beteg műszerfal', 'tableau de bord patients', 'ασθενούς ταμπλό', 'Patienten Armaturenbrett', 'cruscotto paziente', 'แผงควบคุมผู้ป่วย', 'مریض ڈیش بورڈ', 'रोगी डैशबोर्ड', 'Patientes Dashboard', 'dashboard pasien', '患者ダッシュ', '환자의 대시 보드'),
(176, 'view_prescription', 'view prescription', 'প্রেসক্রিপশন দেখতে', 'ver receta', 'عرض وصفة طبية', 'bekijk recept', 'просмотр рецепта', '查看处方', 'tanımını görüntülemek', 'ver prescrição', 'megtekintéséhez recept', 'voir prescription', 'δείτε συνταγή', 'Rezept ansehen', 'visualizzare prescrizione', 'ดูตามใบสั่งแพทย์', 'نسخے دیکھنے کے', 'पर्चे देखने', 'considerabit praescriptione', 'lihat resep', '処方箋を見る', '처방보기'),
(177, 'view_doctor', 'view doctor', 'ডাক্তার দেখতে', 'ver médico', 'عرض الطبيب', 'bekijk arts', 'просмотреть врача', '查看医生', 'doktor görmek', 'vista médico', 'megtekintéséhez orvos', 'voir un médecin', 'δείτε το γιατρό', 'Arzt sehen', 'vista medico', 'ดูหมอ', 'ڈاکٹر کو دیکھنے کے', 'चिकित्सक देखने', 'considerabit medicum', 'lihat dokter', 'ビュー医者', '의사보기'),
(178, 'admit_history', 'admit history', 'ইতিহাস সত্য বলিয়া স্বীকার করা', 'admitir la historia', 'أعترف التاريخ', 'toegeven geschiedenis', 'признать историю', '承认历史', 'tarihi itiraf', 'admitir história', 'elismerem történelem', 'admettre histoire', 'παραδέχονται ιστορία', 'zugeben Geschichte', 'ammettere storia', 'ยอมรับประวัติศาสตร์', 'تاریخ کا اعتراف', 'इतिहास मानता', 'fatentur historiam', 'mengakui sejarah', '歴史を認める', '역사를 인정'),
(179, 'operation_history', 'operation history', 'অপারেশন ইতিহাস', 'historial de operaciones', 'تاريخ العملية', 'bedieningsgeschiedenis', 'истории операций', '手术史', 'operasyon öyküsü', 'histórico de operação', 'működés történelem', 'historique de fonctionnement', 'Ιστορικό λειτουργίας', 'Betrieb Geschichte', 'storia operazione', 'ประวัติศาสตร์การดำเนินงาน', 'آپریشن کی تاریخ', 'आपरेशन इतिहास', 'operatio history', 'sejarah operasi', '運転履歴', '작업 내역'),
(180, 'view_invoice', 'view invoice', 'চালান দেখতে', 'ver la factura', 'عرض الفاتورة', 'bekijk factuur', 'Просмотреть счет', '查看发票', 'fatura görmek', 'ver fatura', 'megtekintéséhez számla', 'voir facture', 'δείτε το τιμολόγιό', 'anzuzeigen Rechnung', 'visualizzare fattura', 'ดูใบแจ้งหนี้', 'انوائس کے لئیے', 'चालान देखने', 'considerabit cautionem', 'lihat faktur', '請求書を表示', '송장보기'),
(181, 'payment_history', 'payment history', 'অর্থ প্রদান ইতিহাস', 'historial de pago', 'تاريخ الدفع', 'betaling geschiedenis', 'История платежей', '付款历史', 'ödeme tarihi', 'histórico de pagamento', 'Fizetési előzmények', 'l\'historique des paiements', 'ιστορικό πληρωμών', 'Zahlungsverhalten', 'storia di pagamento', 'ประวัติการชำระเงิน', 'ادائیگی کی تاریخ', 'भुगतान इतिहास', 'solucionis history', 'riwayat pembayaran', '支払歴', '결제 내역'),
(182, 'view_admit_history', 'view admit history', 'ইতিহাস মানা দেখতে', 'ver admitir historia', 'عرض أعترف التاريخ', 'bekijk toegeven geschiedenis', 'просмотреть признать историю', '查看承认历史', 'tarih kabul görmek', 'ver admitir história', 'megtekintéséhez elismerem történelem', 'voir admettre histoire', 'δείτε παραδέχονται ιστορία', 'sehen zugeben Geschichte', 'visualizzare ammettere storia', 'ดูยอมรับประวัติศาสตร์', 'تاریخ تسلیم دیکھنے کے', 'इतिहास मानता देखने', 'considerabit historiae fatentur', 'lihat mengakui sejarah', '認める履歴を表示', '역사를 인정보기'),
(183, 'view_operation_history', 'view operation history', 'অপারেশন ইতিহাস দেখতে', 'ver el historial de operación', 'عرض تاريخ العملية', 'bekijk bedieningsgeschiedenis', 'просмотр истории операций', '查看操作历史记录', 'işlem geçmişini görüntüleyebilirsiniz', 'visualizar o histórico de operação', 'megtekinteni működés történelem', 'voir l\'historique des opérations', 'δείτε το ιστορικό λειτουργίας', 'Betrieb anzuzeigen Geschichte', 'visualizzare la cronologia delle operazioni', 'ดูประวัติการทำงาน', 'آپریشن کی تاریخ دیکھیں', 'ऑपरेशन के इतिहास को देखने', 'considerabit operationis historiam,', 'melihat riwayat operasi', '操作履歴を表示する', '작업 기록을 보려면'),
(184, 'invoice_list', 'invoice list', 'চালান তালিকা', 'lista de facturas', 'قائمة الفاتورة', 'factuurlijst', 'счет-фактура список', '发票清单', 'fatura listesi', 'lista fatura', 'számla lista', 'liste des factures', 'τιμολογίου λίστα', 'Rechnung Liste', 'Lista fattura', 'รายการใบแจ้งหนี้', 'انوائس کی فہرست', 'चालान सूची', 'cautionem album', 'daftar faktur', '送り状リスト', '송장 목록'),
(185, 'creation_timestamp', 'creation timestamp', 'সৃষ্টি টাইমস্ট্যাম্প', 'fecha y hora de creación', 'الطابع الزمني خلق', 'creatie timestamp', 'Создание метки', '创建时间戳', 'oluşturma zaman damgası', 'timestamp criação', 'létrehozása timestamp', 'horodatage de création', 'timestamp δημιουργία', 'Schaffung Zeitstempel', 'creazione timestamp', 'เวลาสร้าง', 'تخلیق ٹائمسٹیمپ', 'निर्माण टाइमस्टैम्प', 'timestamp creatio', 'penciptaan timestamp', '作成タイムスタンプ', '생성 타임 스탬프'),
(186, 'nurse_dashboard', 'nurse dashboard', 'নার্স ড্যাশবোর্ড', 'dashboard enfermera', 'لوحة أجهزة القياس ممرضة', 'verpleegkundige dashboard', 'Медсестра приборной панели', '护士的仪表板', 'hemşire paneli', 'dashboard enfermeira', 'ápoló műszerfal', 'infirmière tableau de bord', 'ταμπλό νοσοκόμα', 'Krankenschwester Armaturenbrett', 'cruscotto infermiera', 'แผงควบคุมพยาบาล', 'نرس ڈیش بورڈ', 'नर्स डैशबोर्ड', 'nutrix Dashboard', 'dashboard perawat', '看護師のダッシュボード', '간호사 대시 보드'),
(187, 'bed_ward', 'bed ward', 'বিছানা ওয়ার্ড', 'sala de cama', 'جناح السرير', 'bed ward', 'кровать палате', '床病房', 'yatak koğuş', 'cama de enfermaria', 'ágy osztályon', 'salle de lit', 'Ward κρεβάτι', 'Bettenhaus', 'Ward letto', 'หอผู้ป่วยเตียง', 'بستر وارڈ', 'बिस्तर वार्ड', 'stratum pupillo', 'tidur bangsal', 'ベッド病棟', '침대 구'),
(188, 'manage_bed', 'manage bed', 'বিছানা পরিচালনা', 'gestión de la cama', 'إدارة السرير', 'beheren bed', 'управлять кроватью', '管理床', 'yatak yönetmek', 'gerenciar cama', 'kezelni ágy', 'gérer lit', 'διαχείριση κρεβάτι', 'verwalten Bett', 'gestire letto', 'บริหารจัดการเตียง', 'بستر کا انتظام', 'बिस्तर का प्रबंधन', 'stratum curo', 'mengelola tidur', 'ベッドを管理', '침대 관리'),
(189, 'manage_blood_bank', 'manage blood bank', 'ব্লাড ব্যাঙ্ক পরিচালনা', 'gestión de bancos de sangre', 'إدارة بنك الدم', 'beheren bloedbank', 'управлять банком крови', '管理血库', 'kan bankası yönetmek', 'gerenciar banco de sangue', 'kezelni vér bank', 'gérer la banque de sang', 'διαχείριση τράπεζα αίματος', 'verwalten Blutbank', 'gestire banca del sangue', 'จัดการธนาคารเลือด', 'بلڈ بینک کا انتظام', 'ब्लड बैंक का प्रबंधन', 'curo sanguine ripam Iordanis', 'mengelola bank darah', '血液銀行の管理', '혈액 은행 관리'),
(190, 'manage_blood_donor', 'manage blood donor', 'রক্ত দাতা পরিচালনা', 'gestión de donantes de sangre', 'إدارة للمتبرعين بالدم', 'beheren bloeddonor', 'управлять донорской крови', '管理献血', 'kan donör yönetmek', 'gerenciar doador de sangue', 'kezelni véradó', 'gérer les donneurs de sang', 'διαχείριση αιμοδότης', 'verwalten Blutspender', 'gestire donatore di sangue', 'จัดการผู้บริจาคโลหิต', 'بلڈ ڈونر کا انتظام', 'रक्त दाता प्रबंधन', 'curo sanguine donator', 'mengelola donor darah', '供血者を管理する', '혈액 기증자 관리'),
(191, 'add_bed', 'add bed', 'বিছানা যোগ করুন', 'añadir cama', 'إضافة السرير', 'add bed', 'добавить кровать', '加床', 'yatak', 'adicionar cama', 'hozzá ágy', 'ajouter un lit', 'προσθήκη κρεβατιού', 'hinzufügen Bett', 'aggiungere letto', 'เพิ่มเตียง', 'بستر میں شامل کریں', 'बिस्तर जोड़', 'adde cubile', 'tambahkan tidur', 'ベッドを追加', '침대를 추가'),
(192, 'ward', 'ward', 'ওয়ার্ড', 'sala', 'جناح', 'afdeling', 'палата', '病房', 'koğuş', 'enfermaria', 'kórterem', 'Ward', 'πτέρυγα', 'Ward', 'Ward', 'ท้องที่', 'وارڈ', 'परवरिश', 'pupillus', 'bangsal', '病棟', '구'),
(193, 'cabin', 'cabin', 'কেবিন', 'cabina', 'قمرة', 'cabine', 'кабина', '机舱', 'kabin', 'cabine', 'kabin', 'cabine', 'καμπίνα', 'Kabine', 'cabina', 'กระต๊อบ', 'کیبن', 'केबिन', 'Cameram', 'kabin', 'キャビン', '선실'),
(194, 'icu', 'icu', 'ICU', 'icu', 'وحدة العناية المركزة', 'icu', 'ICU', 'ICU', 'icu', 'UTI', 'ICU', 'ICU', 'ΜΕΘ', 'ICU', 'icu', 'ไอซียู', 'آایسییو', 'आईसीयू', 'ICU', 'icu', 'ICU', 'ICU'),
(195, 'edit_bed', 'edit bed', 'বিছানা সম্পাদনা', 'editar cama', 'تعديل سرير', 'bewerk bed', 'редактировать кровать', '编辑床', 'yatak düzenleme', 'editar cama', 'szerkesztés ágy', 'modifier lit', 'επεξεργαστείτε κρεβάτι', 'bearbeiten Bett', 'modificare letto', 'แก้ไขเตียง', 'بستر میں ترمیم کریں', 'बिस्तर संपादित', 'Duis lectus', 'mengedit tidur', 'ベッドを編集', '침대를 편집'),
(196, 'edit_bed_allotment', 'edit bed allotment', 'বিছানা অ্যালটমেন্ট সম্পাদনা', 'editar adjudicación cama', 'تعديل المخصصات السرير', 'bewerk bed toewijzing', 'редактировать кровать выделение', '编辑床配发', 'yatak tahsis düzenleme', 'editar cama loteamento', 'szerkesztés ágy elosztás', 'modifier l\'attribution de lit', 'επεξεργαστείτε κατανομής κρεβάτι', 'bearbeiten Bett Zuteilung', 'modificare letto riparto', 'แก้ไขการจัดสรรเตียง', 'بستر الاٹمنٹ میں ترمیم کریں', 'बिस्तर आवंटन संपादित', 'Duis lectus sorti', 'mengedit tidur penjatahan', 'ベッド割当てを編集', '침대 할당을 편집합니다'),
(197, 'blood_bank_list', 'blood bank list', 'ব্লাড ব্যাঙ্ক তালিকা', 'lista de bancos de sangre', 'قائمة بنك الدم', 'bloedbank lijst', 'Список банков крови', '血库列表', 'kan bankası listesi', 'Lista de banco de sangue', 'vér bank lista', 'liste de banques de sang', 'αίμα λίστα τράπεζας', 'Blutbank Liste', 'elenco banca del sangue', 'รายการธนาคารเลือด', 'بلڈ بینک کی فہرست', 'ब्लड बैंक की सूची', 'sanguinem ripam album', 'Daftar bank darah', '血液銀行のリスト', '혈액 은행 목록'),
(198, 'edit_blood_bank', 'edit blood bank', 'ব্লাড ব্যাঙ্ক সম্পাদনা', 'editar banco de sangre', 'تعديل بنك الدم', 'bewerk bloedbank', 'редактировать банка крови', '编辑血库', 'kan bankası düzenleme', 'editar banco de sangue', 'szerkesztés vér bank', 'modifier banque de sang', 'επεξεργαστείτε τράπεζα αίματος', 'bearbeiten Blutbank', 'modificare banca del sangue', 'แก้ไขธนาคารเลือด', 'بلڈ بینک میں ترمیم کریں', 'ब्लड बैंक को संपादित', 'sanguinem ripam creare', 'mengedit bank darah', '血液バンクを編集', '혈액 은행을 편집합니다'),
(199, 'add_blood_donor', 'add blood donor', 'রক্ত দাতা যোগ করুন', 'añadir donante de sangre', 'إضافة للمتبرعين بالدم', 'voegen bloeddonor', 'добавить донорской крови', '增加献血者', 'kan donör ekleyin', 'adicionar doador de sangue', 'hozzá véradó', 'ajouter donneurs de sang', 'προσθήκη αιμοδότης', 'hinzufügen Blutspender', 'aggiungere donatore di sangue', 'เพิ่มผู้บริจาคโลหิต', 'بلڈ ڈونر شامل', 'रक्त दाता जोड़', 'Addunt sanguinem donator', 'tambahkan donor darah', '献血を追加', '혈액 기증자 추가'),
(200, 'edit_blood_donor', 'edit blood donor', 'রক্ত দাতা সম্পাদনা', 'editar donante de sangre', 'تعديل للمتبرعين بالدم', 'bewerk bloeddonor', 'редактировать донорской крови', '编辑献血', 'kan donör düzenleme', 'editar doador de sangue', 'szerkesztés véradó', 'modifier les donneurs de sang', 'επεξεργαστείτε αιμοδότης', 'bearbeiten Blutspender', 'modificare donatore di sangue', 'แก้ไขผู้บริจาคโลหิต', 'بلڈ ڈونر ترمیم کریں', 'रक्त दाता संपादित', 'recensere sanguinem donator', 'mengedit donor darah', '献血を編集', '혈액 기증자를 편집'),
(201, 'pharmacist_dashboard', 'pharmacist dashboard', 'ফার্মাসিস্ট ড্যাশবোর্ড', 'tablero farmacéutico', 'لوحة أجهزة القياس الصيدلي', 'apotheker dashboard', 'Фармацевт приборной панели', '药剂师仪表', 'eczacı paneli', 'dashboard farmacêutico', 'gyógyszerész műszerfal', 'tableau de bord du pharmacien', 'ταμπλό του φαρμακοποιού', 'Apotheker Armaturenbrett', 'cruscotto farmacista', 'แผงควบคุมเภสัชกร', 'فارماسسٹ ڈیش بورڈ', 'फार्मासिस्ट डैशबोर्ड', 'pharmacist Dashboard', 'dashboard apoteker', '薬剤師のダッシュボード', '약사 대시 보드'),
(202, 'medicine_category', 'medicine category', 'ঔষধ বিষয়শ্রেণীতে', 'Categoría Medicina', 'الطب فئة', 'geneeskunde categorie', 'медицина категории', '医药类', 'tıp kategori', 'medicina categoria', 'gyógyszer kategória', 'médecine catégorie', 'ιατρικής κατηγορία', 'Medizin Kategorie', 'categoria medicina', 'หมวดหมู่การแพทย์', 'دوا قسم', 'दवा वर्ग', 'medicine praedicamentum', 'kategori obat', '薬カテゴリ', '의학 카테고리'),
(203, 'manage_medicine', 'manage medicine', 'ঔষধ পরিচালনা', 'administrar medicamentos', 'إدارة الطب', 'beheren geneeskunde', 'управлять медициной', '医药管理', 'tıp yönetmek', 'gerenciar medicina', 'kezelésére gyógyszert', 'gérer médecine', 'διαχείριση ιατρικής', 'verwalten Medizin', 'gestire medicina', 'บริหารจัดการยา', 'ادویات کا انتظام', 'दवा का प्रबंधन', 'medicine curo', 'mengelola obat', '薬を管理', '약을 관리'),
(204, 'provide_medication', 'provide medication', 'ঔষধ প্রদান', 'proporcionar la medicación', 'توفير الدواء', 'bieden medicatie', 'обеспечить лекарства', '提供用药', 'ilaç sağlamak', 'fornecer medicamentos', 'biztosít gyógyszert', 'fournir des médicaments', 'παρέχουν φάρμακα', 'bieten Medikamente', 'fornire farmaci', 'ให้การรักษาด้วยยา', 'ادویات فراہم', 'दवा प्रदान', 'medicamento parentur', 'menyediakan pengobatan', '薬を提供する', '약물을 제공'),
(205, 'manage_medicine_category', 'manage medicine category', 'ঔষধ বিষয়শ্রেণীতে পরিচালনা', 'gestionar Categoría Medicina', 'إدارة فئة الطب', 'beheren categorie geneeskunde', 'управлять медициной категории', '医药管理类别', 'tıp kategori yönetmek', 'gerenciar categoria medicina', 'kezelni gyógyszer kategória', 'gérer catégorie de médicaments', 'διαχείριση κατηγορία φαρμάκων', 'verwalten Medizin Kategorie', 'gestire categoria medicina', 'จัดการหมวดหมู่การแพทย์', 'دوا زمرے کا انتظام', 'दवा वर्ग का प्रबंधन', 'aget medicina praedicamentum', 'mengelola kategori obat', '薬カテゴリの管理', '의학 카테고리 관리'),
(206, 'medicine_category_list', 'medicine category list', 'ঔষধ বিষয়শ্রেণীতে তালিকা', 'lista de categorías de la medicina', 'قائمة فئة الطب', 'geneeskunde lijst met categorieën', 'Список категорий медицине', '药品分类列表', 'tıp kategori listesi', 'lista de categorias de medicina', 'gyógyszer kategórialista', 'liste des catégories médecine', 'ιατρική λίστα κατηγοριών', 'Medizin Kategorie Liste', 'medicina lista delle categorie', 'รายการประเภทยา', 'طب زمرہ فہرست', 'दवा वर्ग की सूची', 'medicine genus album', 'Obat daftar kategori', '薬カテゴリリスト', '의학 범주 목록'),
(207, 'add_medicine_category', 'add medicine category', 'ঔষধ বিভাগ যোগ করুন', 'añadir Categoría Medicina', 'إضافة فئة الطب', 'add categorie geneeskunde', 'добавить медицину категории', '加药类别', 'tıp kategori eklemek', 'Adicione uma categoria medicina', 'hozzá gyógyszer kategória', 'ajouter la catégorie de la médecine', 'Προσθήκη κατηγορίας ιατρικής', 'Kategorie hinzufügen Medizin', 'aggiungere categoria medicina', 'เพิ่มประเภทยา', 'طب زمرہ شامل', 'दवा श्रेणी जोड़ने', 'adde medicina praedicamentum', 'menambahkan kategori obat', '薬カテゴリを追加', '의학 카테고리를 추가'),
(208, 'medicine_category_name', 'medicine category name', 'ঔষধ বিষয়শ্রেণীতে নাম', 'medicina nombre de la categoría', 'الطب اسم التصنيف', 'geneeskunde categorie naam', 'медицине название категории', '医药类名称', 'tıp kategori adı', 'medicina nome da categoria', 'orvostudomány kategória nevét', 'médecine nom de la catégorie', 'ιατρικής όνομα κατηγορίας', 'Medizin Kategorie Name', 'medicina di nome categoria', 'ชื่อหมวดหมู่ยา', 'دوا قسم کا نام', 'दवा श्रेणी का नाम', 'medicine nomine praedicamentum', 'Obat nama kategori', '薬カテゴリ名', '의학 카테고리의 이름'),
(209, 'medicine_category_description', 'medicine category description', 'ঔষধ বিষয়শ্রেণীতে বর্ণনা', 'medicina descripción de la categoría', 'الطب التصنيف الوصف', 'geneeskunde categorie omschrijving', 'медицина описание категории', '医药类说明', 'tıp kategori açıklaması', 'medicina descrição da categoria', 'gyógyszer kategória leírás', 'médecine description de catégorie', 'ιατρική περιγραφή της κατηγορίας', 'Medizin Beschreibung der Kategorie', 'medicina descrizione categoria', 'คำอธิบายหมวดหมู่ยา', 'دوا قسم وضاحت', 'दवा वर्ग विवरण', 'medicine genus praedicamenti', 'kategori obat deskripsi', '薬カテゴリの説明', '의학 카테고리 설명'),
(210, 'medicine_list', 'medicine list', 'ঔষধ তালিকা', 'lista de medicamentos', 'قائمة الأدوية', 'geneeskunde lijst', 'список лекарств', '药物目录', 'ilaç listesi', 'lista de medicamentos', 'gyógyszer lista', 'médecine liste', 'Λίστα φαρμάκων', 'Medizin Liste', 'medicina elenco', 'รายการยา', 'دوا کی فہرست', 'दवा सूची', 'medicine album', 'Daftar Obat', '薬リスト', '의학 목록'),
(211, 'add_medicine', 'add medicine', 'ঔষধ যোগ করুন', 'añadir la medicina', 'إضافة الدواء', 'voegen geneeskunde', 'добавить медицине', '加药', 'tıp ekleyin', 'adicionar medicina', 'hozzá gyógyszer', 'ajouter médecine', 'προσθέστε το φάρμακο', 'hinzufügen Medizin', 'aggiungere medicina', 'เพิ่มยา', 'ادویات شامل', 'दवा जोड़ना', 'adde medicamentum', 'tambahkan obat', '薬を追加', '약을 추가'),
(212, 'medicine_name', 'medicine name', 'ঔষধ নাম', 'Nombre del medicamento', 'اسم الدواء', 'Naam van de geneeskunde', 'названия лекарства', '药名', 'tıp adı', 'nome do medicamento', 'orvostudomány név', 'nom du médicament', 'Όνομα ιατρικής', 'Medizin Namen', 'Nome del medicinale', 'ชื่อยา', 'دوا کا نام', 'दवा का नाम', 'medicine nomine', 'Nama obat', '薬名', '약 이름'),
(213, 'medicine_catogory', 'medicine catogory', 'ঔষধ শ্রেণী', 'medicina catogory', 'الطب catogory', 'geneeskunde catogory', 'медицины catogory', '医药catogory', 'tıp Kategorideki', 'medicina catogory', 'gyógyszer catogory', 'médecine catogory', 'ιατρικής catogory', 'Medizin catogory', 'medicina catogory', 'catogory ยา', 'دوا catogory', 'दवा catogory', 'medicine catogory', 'Obat catogory', '医学catogory', '의학 catogory'),
(214, 'edit_medicine_category', 'edit medicine category', 'সম্পাদনা ঔষধ বিষয়শ্রেণীতে', 'edit Categoría Medicina', 'تحرير فئة الطب', 'bewerk geneeskunde categorie', 'Редактировать категорию медицине', '编辑医药类', 'düzenleme tıp kategori', 'edição medicina categoria', 'szerkesztés orvostudomány kategória', 'edit médecine catégorie', 'edit ιατρική κατηγορία', 'edit Medizin Kategorie', 'medicina Modifica categoria', 'หมวดหมู่ยาแก้ไข', 'ترمیم دوا قسم', 'संपादित दवा वर्ग', 'edit medicina praedicamentum', 'sunting obat kategori', '薬カテゴリを編集', '편집 의학 카테고리'),
(215, 'edit_medicine', 'edit medicine', 'সম্পাদনা ঔষধ', 'edit medicina', 'تحرير الطب', 'geneeskunde bewerken', 'Редактировать медицине', '编辑药', 'düzenleme tıp', 'edição medicina', 'szerkesztés orvostudomány', 'médecine édition', 'edit ιατρικής', 'edit Medizin', 'Modifica medicina', 'ยาแก้ไข', 'ترمیم دوا', 'संपादित दवा', 'edit medicamentum', 'sunting Obat', '編集薬', '편집 의학'),
(216, 'laboratorist_dashboard', 'laboratorist dashboard,eo', 'ল্যাবরেটরিস্ট ড্যাশবোর্ড', 'laboratorista salpicadero', 'laboratorist لوحة أجهزة القياس', 'laboratorist dashboard', 'laboratorist приборной панели', 'laboratorist仪表板', 'laboratorist paneli', 'laboratorista dashboard', 'laboratorist műszerfal', 'laboratorist tableau de bord', 'laboratorist ταμπλό', 'laboratorist Armaturenbrett', 'laboratorist cruscotto', 'แผงควบคุม laboratorist', 'laboratorist ڈیش بورڈ', 'laboratorist डैशबोर्ड', 'laboratorist Dashboard', 'laboratorist dashboard', 'laboratoristダッシュ', 'laboratorist 대시 보드'),
(217, 'add_diagnosis_report', 'add diagnosis report', 'রোগ নির্ণয়ের রিপোর্ট যোগ করুন', 'agregar informe diagnóstico', 'إضافة تقرير التشخيص', 'voegen diagnose rapport', 'добавить диагноза отчет', '诊断报告', 'tanı raporu ekleyin', 'adicionar relatório de diagnóstico', 'hozzá diagnózis jelentés', 'Ajouter un rapport de diagnostic', 'προσθέστε την έκθεση διάγνωση', 'hinzufügen Diagnose Bericht', 'aggiungere rapporto diagnostico', 'เพิ่มรายงานการวินิจฉัย', 'تشخیص کی رپورٹ میں شامل', 'निदान रिपोर्ट जोड़', 'adde fama diagnosis', 'menambahkan laporan diagnosis', '診断レポートを追加', '진단 보고서를 추가'),
(218, 'report_status', 'report status', 'প্রতিবেদনের অবস্থা', 'estado del informe', 'تقرير الحالة', 'verslag-status', 'отчет о состоянии', '报告状态', 'rapor durumu', 'status do relatório', 'jelentés állapotát', 'état du rapport', 'αναφορά της κατάστασης', 'Status der Meldung', 'Status Report', 'รายงานสถานะ', 'رپورٹ کی حیثیت', 'स्थिति रिपोर्ट', 'fama status', 'Status laporan', 'レポートのステータス', '보고서 상태'),
(219, 'add_diagnostic_report', 'add diagnostic report', 'ডায়গনিস্টিক প্রতিবেদন যোগ করুন', 'agregar informe de diagnóstico', 'إضافة تقرير التشخيص', 'voegen diagnostisch rapport', 'добавить диагностический отчет', '新增诊断报告', 'tanılama raporu ekleyin', 'adicionar relatório de diagnóstico', 'hozzá diagnosztikai jelentés', 'Ajouter un rapport de diagnostic', 'προσθέσει διαγνωστική έκθεση', 'hinzufügen Diagnosebericht', 'aggiungere report diagnostico', 'เพิ่มรายงานการวินิจฉัย', 'تشخیصی رپورٹ میں شامل', 'नैदानिक ​​रिपोर्ट जोड़', 'adde fama Diagnostic', 'menambahkan laporan diagnostik', '診断レポートを追加', '진단 보고서를 추가'),
(220, 'image', 'image', 'চিত্র', 'imagen', 'صورة', 'afbeelding', 'изображение', '图像', 'görüntü', 'imagem', 'kép', 'Image', 'image', 'Bild', 'immagine', 'ภาพ', 'تصویر', 'छवि', 'simulacrum', 'gambar', '画像', '영상'),
(221, 'doc', 'doc', 'Doc', 'doctor', 'دوك', 'dokter', 'доктор', 'DOC', 'doktor', 'doutor', 'doktor', 'doc', 'doc', 'doc', 'doc', 'คุณหมอ', 'ڈاکٹر', 'डॉक्टर', 'doc', 'dokter', 'DOC', '의사'),
(222, 'pdf', 'pdf', 'পিডিএফ', 'pdf', 'PDF', 'pdf', 'PDF', 'PDF', 'pdf', 'pdf', 'pdf', 'pdf', 'pdf', 'pdf', 'pdf', 'รูปแบบไฟล์ PDF', 'پی ڈی ایف', 'पीडीएफ', 'pdf', 'pdf', 'PDF', 'PDF'),
(223, 'excel', 'excel', 'এক্সেল', 'sobresalir', 'تفوق', 'uitmunten', 'превосходить', '高强', 'sivrilmek', 'sobressair', 'excel', 'excel', 'excel', 'excel', 'excel', 'เก่งกว่า', 'ایکسل', 'एक्सेल', 'præesse', 'unggul', 'エクセル', '뛰어나다'),
(224, 'upload_document', 'upload document', 'ডকুমেন্ট আপলোড', 'Cargar documento', 'تحميل الوثيقة', 'uploaden van documenten', 'Загрузить документ', '上传文件', 'belge yüklemek', 'upload de documento', 'feltöltés dokumentum', 'télécharger le document', 'ανεβάσετε το έγγραφο', 'Dokument hochladen', 'Carica documento', 'อัปโหลดเอกสาร', 'دستاویز اپ لوڈ کریں', 'दस्तावेज़ अपलोड', 'upload document', 'meng-upload dokumen', 'ドキュメントをアップロード', '문서를 업로드'),
(225, 'accountant_dashboard', 'accountant dashboard', 'হিসাবরক্ষক ড্যাশবোর্ড', 'tablero contador', 'لوحة أجهزة القياس محاسب', 'accountant dashboard', 'бухгалтером приборной панели', '会计师仪表', 'muhasebeci paneli', 'dashboard contador', 'könyvelő műszerfal', 'tableau de bord comptable', 'ταμπλό λογιστή', 'Buchhalter Armaturenbrett', 'cruscotto contabile', 'แผงควบคุมบัญชี', 'اکاؤنٹنٹ ڈیش بورڈ', 'लेखाकार डैशबोर्ड', 'Ratiocinatore Dashboard', 'dashboard akuntan', '会計士のダッシュボード', '회계사 대시 보드'),
(226, 'invoice / take_payment', 'invoice / take payment', 'চালান / পেমেন্ট নিতে', 'factura / recibir el pago', 'فاتورة / أخذ الأجرة', 'factuur / rekening betalen', 'счета / принимать оплату', '发票/付款', 'fatura / ödeme almak', 'factura / ter o pagamento', 'számla / veszi fizetés', 'facture / prendre le paiement', 'τιμολόγιο / λαμβάνουν πληρωμές', 'Rechnung / Zahlung nehmen', 'fattura / prendere il pagamento', 'ใบแจ้งหนี้ / ใช้การชำระเงิน', 'انوائس / ادائیگی لینا', 'चालान / भुगतान लेना', 'cautionem / accipe mercedes', 'faktur / mengambil pembayaran', '請求/支払いを取る', '송장 / 지불을'),
(227, 'manage_invoice', 'manage invoice', 'চালান পরিচালনা', 'gestionar factura', 'إدارة الفاتورة', 'beheren factuur', 'управлять счета', '发票管理', 'fatura yönetmek', 'gerenciar fatura', 'kezelni számla', 'gérer facture', 'διαχείριση τιμολόγιο', 'Verwaltung Rechnung', 'gestione fattura', 'การจัดการใบแจ้งหนี้', 'انوائس کے انتظام', 'चालान का प्रबंधन', 'aget cautionem', 'mengelola faktur', '請求書を管理する', '송장 관리'),
(228, 'add_invoice', 'add invoice', 'চালান যোগ করুন', 'añadir factura', 'إضافة فاتورة', 'voegen factuur', 'добавить счет-фактура', '加发票', 'fatura ekleyin', 'adicionar fatura', 'hozzá számla', 'ajouter facture', 'προσθήκη τιμολόγιο', 'Rechnung hinzufügen', 'aggiungere fattura', 'เพิ่มใบแจ้งหนี้', 'انوائس میں شامل کریں', 'चालान जोड़', 'add cautionem', 'tambahkan faktur', '請求書を追加', '송장을 추가'),
(229, 'unpaid', 'unpaid', 'অবৈতনিক', 'no pagado', 'غير مدفوع', 'onbetaald', 'неоплаченный', '未付', 'ödenmemiş', 'não remunerado', 'kifizetetlen', 'non rémunéré', 'απλήρωτη', 'unbezahlte', 'non pagato', 'ไม่ได้ค่าจ้าง', 'بلا معاوضہ', 'अवैतनिक', 'est constitutus,', 'belum dibayar', '未払い', '무급'),
(230, 'take_cash_payment', 'take cash payment', 'নগদ টাকা নিতে', 'tomar el pago en efectivo', 'أخذ الأجرة النقدية', 'neem contante betaling', 'принимать оплату наличными', '以现金支付', 'nakit ödeme almak', 'tomar pagamento em dinheiro', 'hogy készpénzes fizetés', 'prendre le paiement en espèces', 'λαμβάνουν καταβολή μετρητών', 'nehmen Barzahlung', 'prendere il pagamento in contanti', 'ใช้เงินสด', 'نقد ادائیگی لینا', 'नकद भुगतान लेना', 'accipere nummis solutio', 'mengambil pembayaran tunai', '現金支払いを取る', '현금 지불을'),
(231, 'paid', 'paid', 'পরিশোধ', 'pagado', 'مدفوع', 'betaald', 'оплачиваемый', '支付', 'ücretli', 'pago', 'fizetett', 'payé', 'καταβληθεί', 'bezahlt', 'pagato', 'ต้องจ่าย', 'ادا کی', 'प्रदत्त', 'solutis', 'dibayar', '支払われた', '지급'),
(232, 'edit_invoice', 'edit invoice', 'চালান সম্পাদনা', 'editar factura', 'تحرير الفاتورة', 'bewerk factuur', 'редактирования счета', '编辑发票', 'fatura düzenlemek', 'editar fatura', 'szerkesztés számla', 'éditer facture', 'επεξεργαστείτε τιμολόγιο', 'Rechnung bearbeiten', 'modificare fattura', 'แก้ไขใบแจ้งหนี้', 'انوائس میں ترمیم کریں', 'चालान संपादित', 'recensere cautionem', 'mengedit faktur', '請求書を編集', '송장을 편집합니다'),
(233, 'edit_nurse', '', 'নার্স সম্পাদনা', '', 'تعديل ممرضة', 'bewerk verpleegkundige', 'редактировать медсестры', '编辑护士', 'hemşire düzenleme', 'editar enfermeira', 'szerkesztés nővér', 'modifier infirmière', 'επεξεργαστείτε νοσοκόμα', 'bearbeiten Krankenschwester', 'modificare infermiera', 'แก้ไขพยาบาล', 'نرس ترمیم کریں', 'नर्स को संपादित', 'recensere nutricem', 'mengedit perawat', '看護師を編集', '간호사 편집');

-- --------------------------------------------------------

--
-- Table structure for table `language_known`
--

CREATE TABLE `language_known` (
  `language_known_id` int(11) NOT NULL,
  `lang_known_name` varchar(100) CHARACTER SET utf8 NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `language_known`
--

INSERT INTO `language_known` (`language_known_id`, `lang_known_name`, `created_date`) VALUES
(1, 'Arabic', '2017-07-20 11:48:05'),
(2, 'English', '2017-07-20 11:48:10');

-- --------------------------------------------------------

--
-- Table structure for table `maya_commission`
--

CREATE TABLE `maya_commission` (
  `id` int(11) NOT NULL,
  `commission_percentage` varchar(10) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `transfer_fees` varchar(10) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `tax` varchar(10) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `minimum_transfer_amount` varchar(10) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `maya_commission`
--

INSERT INTO `maya_commission` (`id`, `commission_percentage`, `transfer_fees`, `tax`, `minimum_transfer_amount`, `created_date`, `updated_date`) VALUES
(2, '30', '2', '0', '50', '2019-01-26 10:08:35', '2019-02-20 18:30:00');

-- --------------------------------------------------------

--
-- Table structure for table `medical_history`
--

CREATE TABLE `medical_history` (
  `medical_historyId` int(11) NOT NULL,
  `specialization_id` int(100) NOT NULL,
  `patient_id` int(100) NOT NULL,
  `dependent_id` int(22) NOT NULL DEFAULT 0,
  `doctor_id` int(11) NOT NULL DEFAULT 0,
  `appointment_id` int(11) NOT NULL DEFAULT 0,
  `medical_questions_id` int(100) NOT NULL,
  `medical_questions_type_id` int(100) NOT NULL,
  `option_values` varchar(4000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `created_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `medical_questions`
--

CREATE TABLE `medical_questions` (
  `medical_questions_id` int(11) NOT NULL,
  `medical_questions_name` varchar(4000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `specialization_id` varchar(11) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `medical_questions_type_id` int(100) NOT NULL DEFAULT 1,
  `option_values` varchar(4000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `status` enum('0','1') COLLATE utf8_unicode_ci NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `medical_questions`
--

INSERT INTO `medical_questions` (`medical_questions_id`, `medical_questions_name`, `specialization_id`, `medical_questions_type_id`, `option_values`, `status`, `created_date`) VALUES
(3, 'please choose the best one from below', '0', 2, 'test,t,three', '1', '2019-05-27 12:55:37');

-- --------------------------------------------------------

--
-- Table structure for table `medical_questions_type`
--

CREATE TABLE `medical_questions_type` (
  `medical_questions_type_id` int(11) NOT NULL,
  `medical_questions_type` varchar(100) NOT NULL,
  `status` enum('0','1') NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `medical_questions_type`
--

INSERT INTO `medical_questions_type` (`medical_questions_type_id`, `medical_questions_type`, `status`, `created_date`) VALUES
(1, 'text', '1', '2017-08-11 09:58:19'),
(2, 'check', '1', '2017-07-27 06:11:31'),
(3, 'radio', '1', '2017-08-11 09:58:32'),
(4, 'select', '1', '2017-07-25 12:05:29');

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `patient_id` int(11) NOT NULL,
  `patient_inc_id` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `nationality_id` varchar(33) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `patient_first_name` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `patient_last_name` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `patient_email` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `patient_password` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `patient_mobile_country_code` varchar(10) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `patient_mobile` varchar(25) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `patient_dob` varchar(10) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `patient_gender` varchar(11) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `patient_address1` varchar(2000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `patient_address2` varchar(2000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `patient_country` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `patient_prof_photo` varchar(155) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `patient_otp_no` varchar(11) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `patient_otp_status` enum('0','1','2') COLLATE utf8_unicode_ci NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`patient_id`, `patient_inc_id`, `nationality_id`, `patient_first_name`, `patient_last_name`, `patient_email`, `patient_password`, `patient_mobile_country_code`, `patient_mobile`, `patient_dob`, `patient_gender`, `patient_address1`, `patient_address2`, `patient_country`, `patient_prof_photo`, `patient_otp_no`, `patient_otp_status`, `created_date`, `updated_date`) VALUES
(1, 'MCP00019', '6598659865', 'Reena', 'Stephen', '', '', '+91', '9500095000', '', '', '', '', '', '', '', '2', '2019-01-11 12:22:51', '2019-02-28 10:06:53');

-- --------------------------------------------------------

--
-- Table structure for table `patient_wallet`
--

CREATE TABLE `patient_wallet` (
  `wallet_id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL,
  `wallet_amount` decimal(25,2) NOT NULL,
  `wallet_currency` varchar(22) NOT NULL DEFAULT '',
  `created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `patient_wallet`
--

INSERT INTO `patient_wallet` (`wallet_id`, `patient_id`, `wallet_amount`, `wallet_currency`, `created_date`, `updated_date`) VALUES
(1, 1, '290.00', 'SAR', '2019-03-06 07:25:45', '2019-04-04 07:17:34');

-- --------------------------------------------------------

--
-- Table structure for table `patient_wallet_transaction`
--

CREATE TABLE `patient_wallet_transaction` (
  `wallet_transaction_id` int(22) NOT NULL,
  `patient_id` int(22) NOT NULL,
  `transaction_amount` decimal(25,2) NOT NULL,
  `transaction_currency` varchar(22) NOT NULL DEFAULT '',
  `transaction_date` date NOT NULL,
  `transaction_ref_no` varchar(3000) NOT NULL DEFAULT '',
  `comments` varchar(3000) NOT NULL DEFAULT '',
  `transaction_status` enum('0','1','2','3','4','5') NOT NULL DEFAULT '0',
  `transaction_receipt_image` varchar(2500) NOT NULL DEFAULT '',
  `transaction_status_note` varchar(155) NOT NULL DEFAULT '',
  `wallet_refund_id` int(11) NOT NULL DEFAULT 0,
  `reject_reason` varchar(111) NOT NULL DEFAULT '',
  `reject_commands` varchar(555) NOT NULL DEFAULT '',
  `created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `patient_wallet_transaction`
--

INSERT INTO `patient_wallet_transaction` (`wallet_transaction_id`, `patient_id`, `transaction_amount`, `transaction_currency`, `transaction_date`, `transaction_ref_no`, `comments`, `transaction_status`, `transaction_receipt_image`, `transaction_status_note`, `wallet_refund_id`, `reject_reason`, `reject_commands`, `created_date`, `updated_date`) VALUES
(1, 1, '100.00', 'SAR', '2019-01-11', '', 'test', '2', '', '', 0, '', '', '2019-01-11 12:34:28', '2019-01-11 12:37:53'),
(4, 1, '95.00', 'SAR', '2019-03-04', '', '', '5', '', 'Amount Refund from Mayaclinic for the Appointment No. APP00005', 1, '', '', '2019-03-04 14:29:59', '2019-03-04 14:29:59'),
(5, 1, '95.00', 'SAR', '2019-03-04', '', '', '5', '', 'Amount Refund from Mayaclinic for the Appointment No. APP00005', 1, '', '', '2019-03-04 14:30:46', '2019-03-04 14:30:46'),
(17, 1, '95.00', 'SAR', '2019-03-06', '', '', '4', '', 'Amount Refund from Mayaclinic for the Claim No. CLM00001', 3, '', '', '2019-03-06 07:21:35', '2019-03-06 07:21:35'),
(18, 1, '95.00', 'SAR', '2019-03-06', '', '', '4', '', 'Amount Refund from Mayaclinic for the Claim No. CLM00001', 3, '', '', '2019-03-06 07:25:44', '2019-03-06 07:25:44'),
(19, 1, '95.00', 'SAR', '2019-03-06', '', '', '4', '', 'Amount Refund from Mayaclinic for the Claim No. CLM00001', 3, '', '', '2019-03-06 08:09:30', '2019-03-06 08:09:30'),
(20, 1, '100.00', 'SAR', '2019-04-04', '', '', '5', '', 'Amount Refund from Mayaclinic for the Appointment No. APP00005', 1, '', '', '2019-04-04 07:17:33', '2019-04-04 07:17:33');

-- --------------------------------------------------------

--
-- Table structure for table `payfort_response`
--

CREATE TABLE `payfort_response` (
  `response_id` int(11) NOT NULL,
  `merchant_reference` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `payfort_id` varchar(155) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `payment_amount` decimal(11,2) NOT NULL DEFAULT 0.00,
  `authorization_code` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `response_message` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `response_code` int(11) NOT NULL DEFAULT 0,
  `card_number` varchar(22) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `card_holder_name` varchar(155) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `signature` varchar(3000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `payment_option` varchar(10) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `expiry_date` varchar(10) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `customer_ip` varchar(22) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `eci` varchar(22) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `language` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `command` varchar(33) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `sdk_token` varchar(150) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `customer_email` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `currency` varchar(11) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `payment_status` varchar(10) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `sadad_olp` varchar(155) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `payment_json_response` varchar(2000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `created_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE `post` (
  `post_id` int(11) NOT NULL,
  `doctor_id` int(50) NOT NULL,
  `post_content` varchar(2000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `post_image` varchar(2000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `post_location` varchar(2000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `post_tag` varchar(2000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `post_video` varchar(2000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `post_date` date NOT NULL,
  `post_status` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT 'Active',
  `created_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`post_id`, `doctor_id`, `post_content`, `post_image`, `post_location`, `post_tag`, `post_video`, `post_date`, `post_status`, `created_date`) VALUES
(1, 1, 'Test Content', '', '', 'This is post tag', '', '2018-12-27', 'Active', '2018-12-27 06:48:34');

-- --------------------------------------------------------

--
-- Table structure for table `post_like`
--

CREATE TABLE `post_like` (
  `like_id` int(11) NOT NULL,
  `post_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_type` int(5) DEFAULT NULL,
  `status` int(5) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `promotion`
--

CREATE TABLE `promotion` (
  `promotion_id` int(11) NOT NULL,
  `specialization_id` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `promotion_type` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `promotion_title` varchar(200) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `promotion_description` varchar(2000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `promotion_code` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `promotion_amount` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `promotion_fromDate` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `promotion_toDate` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `created_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `promotion_tracking`
--

CREATE TABLE `promotion_tracking` (
  `promotion_tracking_id` int(11) NOT NULL,
  `promotion_id` int(11) NOT NULL,
  `promotion_code` varchar(55) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `patient_id` int(11) NOT NULL,
  `promotion_applied_date` varchar(55) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `promotion_amount` decimal(5,2) NOT NULL,
  `appointment_amount` decimal(5,2) NOT NULL,
  `appointment_paid_amount` decimal(5,2) NOT NULL,
  `promotion_applied_appointment_id` varchar(10) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `created_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `promotion_tracking`
--

INSERT INTO `promotion_tracking` (`promotion_tracking_id`, `promotion_id`, `promotion_code`, `patient_id`, `promotion_applied_date`, `promotion_amount`, `appointment_amount`, `appointment_paid_amount`, `promotion_applied_appointment_id`, `created_date`) VALUES
(2, 2, 'TEST50', 1, '2019-01-11', '5.00', '100.00', '95.00', '3', '2019-03-06 05:57:59');

-- --------------------------------------------------------

--
-- Table structure for table `push_notification_data`
--

CREATE TABLE `push_notification_data` (
  `notification_id` int(11) NOT NULL,
  `doctor_id` int(11) NOT NULL DEFAULT 0,
  `patient_id` int(11) NOT NULL DEFAULT 0,
  `user_type` int(5) NOT NULL DEFAULT 0,
  `notification_title` varchar(1000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `notification_message` varchar(3000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `notification_data` varchar(4000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `notification_type` enum('0','1','2','3','4','5','6','7','8','9') COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `created_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `settings`
--

CREATE TABLE `settings` (
  `settings_id` int(11) NOT NULL,
  `type` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `description` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `settings`
--

INSERT INTO `settings` (`settings_id`, `type`, `description`) VALUES
(1, 'system_name', '|Maya Clinic|'),
(7, 'system_email', 'mayaclinic@gmail.com'),
(2, 'system_title', 'Maya Clinic Admin'),
(3, 'address', 'Chennai, India'),
(4, 'phone', '9000090000'),
(5, 'paypal_email', 'paypal@paypal.com'),
(6, 'currency', 'SAR');

-- --------------------------------------------------------

--
-- Table structure for table `specialization`
--

CREATE TABLE `specialization` (
  `specialization_id` int(11) NOT NULL,
  `name` varchar(110) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `specialization_arabic_name` varchar(155) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `description` varchar(2000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `specialization_image` varchar(2000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `created_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `specialization`
--

INSERT INTO `specialization` (`specialization_id`, `name`, `specialization_arabic_name`, `description`, `specialization_image`, `created_date`) VALUES
(1, 'OBGYN', 'نساء و ولادة', 'Entire Body', 'Screen_Shot_2018-01-29_at_1.12_.36_AM_.png', '2018-04-29 09:10:32'),
(2, 'Orthopedic', 'عظام', '', 'Screen_Shot_2018-01-29_at_1.12_.49_AM_.png', '2018-04-29 09:10:32'),
(3, 'Emergency', 'طب طوارئ', '', 'Screen_Shot_2018-01-29_at_1.12_.10_AM_.png', '2018-04-29 09:10:32'),
(4, 'ENT', 'الانف و الاذن و الحنجرة', '', 'Screen_Shot_2018-01-29_at_1.13_.16_AM_.png', '2018-04-29 09:10:32'),
(5, 'Nutrition', 'التغذية', 'New Specialization', 'Screen_Shot_2018-01-29_at_1.12_.19_AM_.png', '2018-04-29 09:10:32'),
(6, 'Psychiatric', 'علم النفس', 'This is favourite doctors list', 'Screen_Shot_2018-01-29_at_1.13_.29_AM_.png', '2018-04-29 09:10:32'),
(7, 'Forensic Medicine ', 'الطب الشرعي', '', 'Screen_Shot_2018-04-14_at_1.32_.40_PM_.png', '2018-04-29 09:10:32'),
(8, 'Laboratories', 'مختبرات', '', 'Screen_Shot_2018-01-29_at_1.11_.58_AM_.png', '2018-04-29 09:10:32'),
(9, 'Maya Doctors', 'أطباء مايا', 'Our Selective doctors ', 'Screen_Shot_2018-02-15_at_4.27_.32_PM_.png', '2018-04-29 09:10:32'),
(10, 'Dermatology ', 'الجـلدية', '', 'Screen_Shot_2018-04-14_at_1.33_.19_PM_.png', '2018-04-29 09:10:32'),
(11, 'Pediatric', 'أطفـال', '', 'Screen_Shot_2018-04-14_at_1.17_.30_PM_.png', '2018-04-29 09:10:32'),
(12, 'Internal Medicine', 'باطنيـة', '', 'Screen_Shot_2018-04-14_at_1.19_.13_PM_.png', '2018-04-29 09:10:32'),
(13, 'Cardiology', 'قلب', '', 'Screen_Shot_2018-04-14_at_1.18_.55_PM_.png', '2018-10-21 18:36:44'),
(14, 'Anesthesia', 'تخدير', '', 'Screen_Shot_2018-04-14_at_1.19_.47_PM_.png', '2018-04-29 09:10:32'),
(15, 'Pulmonology', 'صدرية', '', 'Screen_Shot_2018-04-14_at_1.18_.19_PM_.png', '2018-10-21 18:35:55'),
(16, 'Nephrology', 'كلى', '', 'Screen_Shot_2018-04-14_at_1.18_.39_PM_.png', '2018-10-21 18:35:20'),
(17, 'Family Medicine', 'طب الأسرة', '', 'Screen_Shot_2018-04-14_at_1.20_.19_PM_.png', '2018-04-29 09:10:32'),
(18, 'Surgery', 'جراحة', '', 'Screen_Shot_2018-04-14_at_1.19_.28_PM_.png', '2018-04-29 09:10:32'),
(19, 'Oncology', 'علم الأورام', '', 'Screen_Shot_2018-09-10_at_1.39_.46_AM_.png', '2018-09-09 22:13:05'),
(20, 'Plastic', 'التجميل', '', 'Screen_Shot_2018-09-16_at_3.14_.17_AM_.png', '2018-09-17 09:10:16'),
(21, 'Ophthalmology', 'العيون', '', 'Screen_Shot_2018-10-01_at_11.39_.42_PM_.png', '2018-10-01 20:07:03'),
(22, 'Dentistry', 'أسنان', '', 'Screen_Shot_2018-10-14_at_12.37_.22_AM_.png', '2018-10-13 21:04:38'),
(23, 'Pharmacology', 'الصيدله', '', 'Screen_Shot_2018-04-14_at_1.19_.47_PM_1.png', '2018-10-15 13:45:52'),
(24, 'Maxillofacial Surgery', 'جراحة الوجه و الفكين ', '', 'Screen_Shot_2018-01-29_at_1.13_.05_AM_.png', '2018-10-20 10:30:01'),
(25, 'Neurology', 'الأعصاب', '', 'Screen_Shot_2018-10-21_at_10.17_.01_PM_.png', '2018-10-21 18:42:06'),
(26, 'Rheumatology', 'الروماتيزم', '', 'Screen_Shot_2018-10-21_at_10.21_.03_PM_.png', '2018-10-21 18:46:07'),
(27, 'Rehabilitation', 'التأهيل الصحي', '', 'Screen_Shot_2018-01-29_at_1.11_.37_AM_.png', '2018-10-24 05:32:11'),
(28, 'Endocrinology', 'الغدد', '', 'Screen_Shot_2018-04-14_at_1.32_.40_PM_1.png', '2018-10-24 05:34:11'),
(29, 'Paramedic', 'اسعافات أولية', '', 'Screen_Shot_2018-10-27_at_2.13_.44_AM_.png', '2018-10-26 22:38:20'),
(30, 'Pain Management', 'ادارة الألم', '', 'Screen_Shot_2018-01-29_at_1.13_.29_AM_1.png', '2018-11-01 20:53:34'),
(31, 'Infertility', 'العقم', '', 'Screen_Shot_2018-11-02_at_12.32_.03_AM_.png', '2018-11-01 20:56:04'),
(32, 'General Practitioner ', 'طبيب عام', '', 'Screen_Shot_2018-01-29_at_1.11_.37_AM_1.png', '2018-11-02 18:57:17'),
(33, 'Physiotherapy ', 'العلاج الطبيعي', '', 'Screen_Shot_2018-11-03_at_7.32_.02_PM_.png', '2018-11-03 15:56:05'),
(34, 'Urology', 'المسالك البولية', '', 'Screen_Shot_2018-04-14_at_1.18_.39_PM_1.png', '2018-11-06 14:15:38');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `transaction_id` int(11) NOT NULL,
  `maya_transaction_id` varchar(250) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `sdk_token` varchar(2000) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `merchant_reference` varchar(155) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `transaction_payfort_id` varchar(55) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `payment_option` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `transaction_date` datetime NOT NULL,
  `transaction_amount` decimal(8,2) NOT NULL,
  `transaction_currency` varchar(55) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `transaction_status` int(11) NOT NULL,
  `transaction_status_note` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `patient_id` int(11) NOT NULL,
  `maya_patient_id` varchar(11) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `appointment_date` date NOT NULL,
  `appointment_time` varchar(11) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `doctor_id` int(11) NOT NULL,
  `transaction_card_number` varchar(55) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `transaction_customer_ip` varchar(55) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `transaction_card_expdate` varchar(22) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `created_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`transaction_id`, `maya_transaction_id`, `sdk_token`, `merchant_reference`, `transaction_payfort_id`, `payment_option`, `transaction_date`, `transaction_amount`, `transaction_currency`, `transaction_status`, `transaction_status_note`, `patient_id`, `maya_patient_id`, `appointment_date`, `appointment_time`, `doctor_id`, `transaction_card_number`, `transaction_customer_ip`, `transaction_card_expdate`, `created_date`) VALUES
(183, '95000950001549350528731', '80ACB591E93956DBE053321E320AE95B', '1549350530254', '154934947400091848', 'VISA', '2019-02-05 12:38:00', '1.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2019-02-05', '12:40 PM', 1, '400555******0001', '182.72.187.97', '2105', '2019-02-05 01:39:27'),
(184, '95000950001549447073096', '8126D67FFFBB5368E053321E320A0BB9', '1549447074367', '154944599800018837', 'VISA', '2019-02-06 15:27:00', '1.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2019-02-06', '3:29 PM', 1, '400555******0001', '182.72.187.97', '2105', '2019-02-06 04:28:14'),
(185, '1549449738339', '81380AEAE2CF26E3E053321E320A074C', '', '', 'VISA', '2018-03-02 12:30:00', '50.00', 'SAR', 2, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '', '', '', '2019-02-06 05:12:33'),
(186, '95000950001549449744123', '81380AEAE2D026E3E053321E320A074C', '1_9500095000_1549449744124', '', '', '2019-02-06 16:12:00', '900.00', 'SAR', 2, 'Transaction canceled by payer', 1, 'MCP0001', '2019-02-09', '11:50 AM', 2, '', '', '', '2019-02-06 05:13:27'),
(187, '95000950001549449827065', '8137E2EDFD452753E053321E320A8D97', '1_9500095000_1549449827066', '', '', '2019-02-06 16:13:00', '900.00', 'SAR', 2, 'Transaction canceled by payer', 1, 'MCP0001', '2019-02-09', '11:50 AM', 2, '', '', '', '2019-02-06 05:14:15'),
(188, '95000950001549449912077', '8137EF22B5EC26E9E053321E320AFCD7', '1549449913291', '154944887600019352', 'VISA', '2019-02-06 16:15:00', '1.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2019-02-06', '4:19 PM', 1, '400555******0001', '182.72.187.97', '2105', '2019-02-06 05:16:12'),
(189, '95000950001549522991477', '813A003CC18C3897E053321E320A4BE4', '1_9500095000_1549522991481', '', '', '2019-02-07 12:33:00', '100.00', 'SAR', 3, 'Device ID mismatch', 1, 'MCP0001', '2019-02-11', '9:30 AM', 1, '', '', '', '2019-02-07 01:33:14'),
(190, '95000950001549523198366', '813AEF1091B73891E053321E320A51C7', '1_9500095000_1549523198377', '', '', '2019-02-07 12:36:00', '100.00', 'SAR', 1, 'Payment Initiated', 1, 'MCP0001', '2019-02-11', '9:30 AM', 1, '', '', '', '2019-02-07 01:36:37'),
(191, '95000950001549524147286', '8139CF9C043F38BBE053321E320ADEBA', '1_9500095000_1549524147290', '', '', '2019-02-07 12:52:00', '900.00', 'SAR', 3, 'Device ID mismatch', 1, 'MCP0001', '2019-02-11', '2:08 PM', 2, '', '', '', '2019-02-07 01:52:29'),
(192, '95000950001549524789485', '813AEF1091C43891E053321E320A51C7', '1_9500095000_1549524789499', '', '', '2019-02-07 13:03:00', '900.00', 'SAR', 2, 'Transaction canceled by payer', 1, 'MCP0001', '2019-02-11', '2:08 PM', 2, '', '', '', '2019-02-07 02:03:16'),
(193, '95000950001549525290244', '8139D0829B193889E053321E320AAB69', '1_9500095000_1549525290257', '', '', '2019-02-07 13:11:00', '900.00', 'SAR', 2, 'Transaction canceled by payer', 1, 'MCP0001', '2019-02-11', '2:11 PM', 2, '', '', '', '2019-02-07 02:11:38'),
(194, '95000950001549525322588', '813B33CF070F3893E053321E320A687B', '1549525323855', '154952424400036570', 'VISA', '2019-02-07 13:12:00', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2019-02-11', '9:32 AM', 1, '400555******0001', '103.16.202.6', '3412', '2019-02-07 02:12:22'),
(195, '95000950001549525721733', '8139CF9C044C38BBE053321E320ADEBA', '1549525722664', '154952464200036617', 'VISA', '2019-02-07 13:18:00', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2019-02-12', '12:06 AM', 1, '400555******0001', '103.16.202.6', '3412', '2019-02-07 02:19:00'),
(196, '95000950001549538270108', '814BD10DB4840482E053321E320AB053', '1549538271140', '154953720800038298', 'VISA', '2019-02-07 16:47:00', '1.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2019-02-07', '4:56 PM', 1, '400555******0001', '182.72.187.97', '2105', '2019-02-07 05:48:27'),
(197, '95000950001549630650436', '81524010DD410402E053321E320A0173', '1_9500095000_1549630650441', '', '', '2019-02-08 18:27:00', '900.00', 'SAR', 2, 'Transaction canceled by payer', 1, 'MCP0001', '2019-02-11', '2:08 PM', 2, '', '', '', '2019-02-08 07:27:36'),
(198, '1549793082162', '814BD10DB5BF0482E053321E320AB053', '', '', 'VISA', '2018-03-02 12:30:00', '50.00', 'SAR', 2, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '', '', '', '2019-02-10 04:34:53'),
(199, '1549808690732', '818B48CC6D220753E053321E320ABEA4', '1549808690732', '154980765900060879', 'VISA', '2018-03-02 12:30:00', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '49.206.114.37', '2105', '2019-02-10 08:56:06'),
(200, '1549865914782', '818B48CC6D440753E053321E320ABEA4', '', '', 'VISA', '2018-03-02 12:30:00', '50.00', 'SAR', 2, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '', '', '', '2019-02-11 00:48:43'),
(201, '95000950001549866061642', '818B48CC6D460753E053321E320ABEA4', '1549866062877', '154986497800064480', 'VISA', '2019-02-11 11:51:00', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2019-02-14', '9:00 AM', 1, '400555******0001', '182.72.187.97', '3412', '2019-02-11 00:51:34'),
(202, '1549870248932', '8188CC356A5E075BE053321E320A03D0', '1549870248932', '154986919300065135', 'VISA', '2018-03-02 12:30:00', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.73.228.182', '3412', '2019-02-11 02:01:44'),
(203, '1549871076125', '8188CC356A63075BE053321E320A03D0', '1549871076125', '154987003000065253', 'VISA', '2018-03-02 12:30:00', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.73.228.182', '3412', '2019-02-11 02:15:41'),
(204, '1549871495571', '818927986FF00757E053321E320AAA40', '1549871495571', '154987041100065299', 'VISA', '2018-03-02 12:30:00', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.73.228.182', '3412', '2019-02-11 02:22:00'),
(205, '1549871775379', '818927986FF40757E053321E320AAA40', '1549871775379', '154987069200065346', 'VISA', '2018-03-02 12:30:00', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.73.228.182', '3412', '2019-02-11 02:26:41'),
(206, '1549872147495', '818928E9D4F20761E053321E320AF661', '1549872147495', '154987107400065395', 'VISA', '2018-03-02 12:30:00', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.73.228.182', '3412', '2019-02-11 02:33:03'),
(207, '95000950001549880792809', '819AFA989623074BE053321E320AAF1C', '1549880793867', '154987971100066964', 'VISA', '2019-02-11 15:56:00', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2019-02-14', '9:06 AM', 1, '400555******0001', '182.72.187.97', '3412', '2019-02-11 04:57:01'),
(208, '1549882147418', '818952C62B1B075DE053321E320A015B', '1549882147418', '154988108300067180', 'VISA', '2018-03-02 12:30:00', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.73.228.182', '3412', '2019-02-11 05:19:52'),
(209, '95000950001549882559445', '8188CC356AA8075BE053321E320A03D0', '1549882560492', '154988146800067236', 'VISA', '2019-02-11 16:25:00', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2019-02-14', '3:25 PM', 2, '400555******0001', '182.72.187.97', '3412', '2019-02-11 05:26:19'),
(210, '1549882590111', '8188AC05478B0763E053321E320AE5EF', '1549882590111', '154988150500067255', 'VISA', '2018-03-02 12:30:00', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.73.228.182', '3412', '2019-02-11 05:26:53'),
(211, '1549882730211', '8188AC05478F0763E053321E320AE5EF', '1549882730211', '154988164900067297', 'VISA', '2018-03-02 12:30:00', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.73.228.182', '3412', '2019-02-11 05:29:18'),
(212, '95000950001549882786176', '8188AC0547930763E053321E320AE5EF', '1549882787270', '154988170500067304', 'VISA', '2019-02-11 16:29:00', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2019-02-14', '3:28 PM', 2, '400555******0001', '182.72.187.97', '3412', '2019-02-11 05:30:15'),
(213, '1549882916633', '819AFA989639074BE053321E320AAF1C', '1549882916633', '154988183000067317', 'VISA', '2018-03-02 12:30:00', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.73.228.182', '3412', '2019-02-11 05:32:18'),
(214, '95000950001549883098902', '818952C62B2F075DE053321E320A015B', '1549883100124', '154988201600067343', 'VISA', '2019-02-11 16:34:00', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2019-02-13', '3:30 PM', 2, '400555******0001', '182.72.187.97', '3412', '2019-02-11 05:35:25'),
(215, '95000950001549883139690', '8188CC356AAE075BE053321E320A03D0', '1_9500095000_1549883139701', '', '', '2019-02-11 16:35:00', '900.00', 'SAR', 1, 'Payment Initiated', 1, 'MCP0001', '2019-02-13', '3:30 PM', 2, '', '', '', '2019-02-11 05:35:37'),
(216, '95000950001549883473585', '8188B6049683075FE053321E320AFD51', '1_9500095000_1549883473593', '', '', '2019-02-11 16:41:00', '900.00', 'SAR', 1, 'Payment Initiated', 1, 'MCP0001', '2019-02-14', '3:37 PM', 2, '', '', '', '2019-02-11 05:41:11'),
(217, '95000950001549883485609', '8188B6049685075FE053321E320AFD51', '1549883486624', '154988244200067409', 'VISA', '2019-02-11 16:41:00', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2019-02-14', '3:37 PM', 2, '400555******0001', '182.72.187.97', '8709', '2019-02-11 05:42:32'),
(218, '95000950001549883563290', '8188B6049687075FE053321E320AFD51', '1549883565246', '154988247400067414', 'VISA', '2019-02-11 16:42:00', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2019-02-14', '3:37 PM', 2, '400555******0001', '182.72.187.97', '3412', '2019-02-11 05:43:02'),
(219, '1549886694805', '818C38C71ED20759E053321E320AFFCF', '1549886694805', '154988561200067773', 'VISA', '2018-03-02 12:30:00', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.73.228.182', '3412', '2019-02-11 06:35:21'),
(220, '1549887165877', '818952C62B4F075DE053321E320A015B', '1549887165877', '154988608600067842', 'VISA', '2018-03-02 12:30:00', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.73.228.182', '3412', '2019-02-11 06:43:15'),
(221, '1549887494630', '818952C62B5C075DE053321E320A015B', '1549887494630', '154988641000067881', 'VISA', '2018-03-02 12:30:00', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.73.228.182', '3412', '2019-02-11 06:48:39'),
(222, '1549888162288', '8188B60496C1075FE053321E320AFD51', '1549888162288', '154988707300067949', 'VISA', '2018-03-02 12:30:00', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.73.228.182', '3412', '2019-02-11 06:59:42'),
(223, '95000950001549888447154', '818952C62B69075DE053321E320A015B', '1549888448242', '154988736600068031', 'VISA', '2019-02-11 18:04:00', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2019-02-11', '6:08 PM', 1, '400555******0001', '182.72.187.97', '3901', '2019-02-11 07:04:37'),
(224, '95000950001549888489142', '818928E9D5230761E053321E320AF661', '1549888490123', '154988740900068041', 'VISA', '2019-02-11 18:04:00', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2019-02-11', '6:08 PM', 1, '400555******0001', '182.72.187.97', '3412', '2019-02-11 07:05:18'),
(225, '95000950001549888565809', '8188B60496C4075FE053321E320AFD51', '1_9500095000_1549888565811', '', '', '2019-02-11 18:06:00', '900.00', 'SAR', 1, 'Payment Initiated', 1, 'MCP0001', '2019-02-14', '3:31 PM', 2, '', '', '', '2019-02-11 07:06:05'),
(226, '95000950001549888603788', '818C38C71EEF0759E053321E320AFFCF', '1_9500095000_1549888603790', '', '', '2019-02-11 18:06:00', '0.00', 'SAR', 3, 'Invalid amount', 1, 'MCP0001', '2019-02-11', '6:10 PM', 1, '', '', '', '2019-02-11 07:06:46'),
(227, '95000950001549888630732', '818C38C71EF00759E053321E320AFFCF', '1549888631735', '154988754200068072', 'VISA', '2019-02-11 18:07:00', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2019-02-11', '6:12 PM', 1, '400555******0001', '182.72.187.97', '3412', '2019-02-11 07:07:32'),
(228, '1549888865302', '8188B60496C5075FE053321E320AFD51', '1549888865302', '154988778500068145', 'VISA', '2018-03-02 12:30:00', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.73.228.182', '3412', '2019-02-11 07:11:34'),
(229, '1549890301610', '818928E9D54A0761E053321E320AF661', '1549890301610', '154988921500068436', 'VISA', '2018-03-02 12:30:00', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.73.228.182', '3412', '2019-02-11 07:35:24'),
(230, '1549891455402', '8188B60496EC075FE053321E320AFD51', '1549891455402', '154989036900068698', 'VISA', '2018-03-02 12:30:00', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.73.228.182', '3412', '2019-02-11 07:54:39'),
(231, '1549891733050', '8188B60496EE075FE053321E320AFD51', '1549891733050', '154989064400068733', 'VISA', '2018-03-02 12:30:00', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.73.228.182', '3412', '2019-02-11 07:59:14'),
(232, '1549892095357', '819AFA98969E074BE053321E320AAF1C', '1549892095357', '154989101300068789', 'VISA', '2018-03-02 12:30:00', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.73.228.182', '3412', '2019-02-11 08:05:22'),
(233, '1549949692262', '81A503E36BDE0751E053321E320ABEF2', '1549949692262', '154994863500087007', 'MADA', '2018-03-02 12:30:00', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '529741******5689', '182.73.228.182', '2105', '2019-02-12 00:05:42'),
(234, '1549951813208', '819B69A3A14D074FE053321E320AC2BE', '1549951813208', '154995072600087169', 'VISA', '0000-00-00 00:00:00', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.73.228.182', '3412', '2019-02-12 00:40:39'),
(235, '1549954165951', '81A503E36BFC0751E053321E320ABEF2', '1549954165951', '154995308800087489', 'VISA', '2019-02-12 00:00:00', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.73.228.182', '3412', '2019-02-12 01:19:58'),
(236, '1549955406504', '8188CC356CD4075BE053321E320A03D0', '1549955406504', '154995433300087638', 'VISA', '0000-00-00 00:00:00', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.73.228.182', '3412', '2019-02-12 01:40:44'),
(237, '1549955840213', '8188CC356CDA075BE053321E320A03D0', '1549955840213', '154995476700087710', 'VISA', '2019-02-12 12:47:21', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.73.228.182', '3412', '2019-02-12 01:47:58'),
(238, '95000950001549964358112', '819B69A3A17B074FE053321E320AC2BE', '1549964359311', '154996327100089021', 'VISA', '2019-02-12 15:09:00', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2019-02-12', '3:06 PM', 1, '400555******0001', '182.72.187.97', '2105', '2019-02-12 04:09:45'),
(239, '1550127896131', '819B69A3A544074FE053321E320AC2BE', '1550127896131', '155012684900024803', 'VISA', '2019-02-14 12:34:57', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.73.228.182', '3412', '2019-02-14 01:36:13'),
(240, '1550137202385', '818B48CC744D0753E053321E320ABEA4', '1550137202385', '155013610800026097', 'VISA', '2019-02-14 15:10:04', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.73.228.182', '3412', '2019-02-14 04:10:26'),
(241, '1550137939785', '819B69A3A579074FE053321E320AC2BE', '1550137939785', '155013684700026245', 'VISA', '2019-02-14 15:22:20', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.73.228.182', '3412', '2019-02-14 04:22:43'),
(242, '1550138663031', '818928E9DB870761E053321E320AF661', '1550138663031', '155013756800026432', 'VISA', '2019-02-14 15:34:24', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.73.228.182', '3412', '2019-02-14 04:34:45'),
(243, '1550139877760', '819B69A3A580074FE053321E320AC2BE', '1550139877760', '155013878400026582', 'VISA', '2019-02-14 15:54:38', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.73.228.182', '3412', '2019-02-14 04:55:01'),
(244, '1550140056173', '818B48CC74660753E053321E320ABEA4', '1550140056173', '155013895900026604', 'VISA', '2019-02-14 15:57:37', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.73.228.182', '3412', '2019-02-14 04:57:55'),
(245, '1550140507203', '819B69A3A58C074FE053321E320AC2BE', '1550140507203', '155013941200026677', 'VISA', '2019-02-14 16:05:08', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.73.228.182', '3412', '2019-02-14 05:05:28'),
(246, '1550140636870', '818B48CC74670753E053321E320ABEA4', '1550140636870', '155013954000026711', 'VISA', '2019-02-14 16:07:18', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.73.228.182', '3412', '2019-02-14 05:07:36'),
(247, '1550214837557', '819B69A3A6B0074FE053321E320AC2BE', '', '', 'VISA', '2018-03-02 12:30:00', '50.00', 'SAR', 2, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '', '', '', '2019-02-15 01:44:05'),
(248, '1550214865102', '81A2F9E065180755E053321E320A4728', '1550214865102', '155021376400034051', 'VISA', '2019-02-15 12:44:26', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.73.228.182', '3412', '2019-02-15 01:44:44'),
(249, '1550216517916', '818C38C722850759E053321E320AFFCF', '1550216517916', '155021541800034165', 'VISA', '2019-02-15 13:11:59', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.73.228.182', '3412', '2019-02-15 02:12:17'),
(250, '1550216760418', '8188CC356F5E075BE053321E320A03D0', '1550216760418', '155021566600034176', 'VISA', '2019-02-15 13:16:01', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.73.228.182', '3412', '2019-02-15 02:16:24'),
(251, '123456781550227565245', '8188CC356F80075BE053321E320A03D0', '1550227566459', '155022643600035195', 'VISA', '2019-02-15 16:16:00', '100.00', 'SAR', 4, 'Success', 2, 'MCP0002', '2019-02-15', '4:20 PM', 1, '400555******0001', '182.72.187.97', '2105', '2019-02-15 05:15:56'),
(252, '123456781550228202472', '81A2F9E0654D0755E053321E320A4728', '1550228203466', '155022706800035251', 'VISA', '2019-02-15 16:26:00', '100.00', 'SAR', 4, 'Success', 2, 'MCP0002', '2019-02-15', '5:00 PM', 1, '400555******0001', '182.72.187.97', '2105', '2019-02-15 05:26:28'),
(253, '123456781550671155208', '82542CFE398B50EEE053321E320AEB90', '1550671156312', '155066999400090004', 'VISA', '2019-02-20 19:29:00', '100.00', 'SAR', 4, 'Success', 2, 'MCP0002', '2019-02-23', '12:40 PM', 1, '400555******0001', '182.72.187.97', '3412', '2019-02-20 08:28:47'),
(254, '95000950001550812919901', '82698E33F0912B66E053321E320AD1C1', '1550812920945', '155081180300001191', 'VISA', '2019-02-22 10:51:00', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2019-02-25', '10:00 AM', 1, '400555******0001', '182.72.187.97', '3412', '2019-02-21 23:52:21'),
(255, '95000950001550813069023', '82698E33F0942B66E053321E320AD1C1', '1550813070060', '155081197200001194', 'MADA', '2019-02-22 10:54:00', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2019-02-25', '10:15 AM', 1, '529741******5689', '182.72.187.97', '2105', '2019-02-21 23:55:06'),
(256, '1551359579692', '82E5FB523261188BE053321E320A0BCB', '1551359579692', '155135845100050520', 'VISA', '2019-02-28 18:43:01', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.73.228.182', '3412', '2019-02-28 07:43:27'),
(257, '74182612831552981521671', '845EC3B5D4281639E053321E320A26A3', '1552981522660', '155298034800007491', 'VISA', '2019-03-19 13:15:00', '100.00', 'SAR', 4, 'Success', 17, 'MCP0017', '2019-03-22', '8:15 AM', 1, '400555******0001', '182.72.187.97', '3412', '2019-03-19 02:15:52'),
(258, '74182612831555334919719', '86917D5A27C462A5E053321E320A2A6C', '1555334920884', '155533366600058801', 'VISA', '2019-04-15 18:58:00', '100.00', 'SAR', 4, 'Success', 17, 'MCP0017', '2019-04-15', '10:00 PM', 1, '400555******0001', '182.72.187.97', '3412', '2019-04-15 07:59:00'),
(259, '95000950001557819957910', '88B67D2B389849E5E053321E320A49B1', '1557819958901', '', 'VISA', '2019-05-14 13:15:00', '90.00', 'SAR', 3, 'Invalid card number', 1, 'MCP0001', '2019-05-14', '1:30 PM', 1, '', '182.72.187.97', '', '2019-05-14 02:20:05'),
(260, '95000950001557820214946', '88B67D2B389C49E5E053321E320A49B1', '1557820215754', '', 'VISA', '2019-05-14 13:20:00', '90.00', 'SAR', 3, 'Invalid card number', 1, 'MCP0001', '2019-05-14', '1:30 PM', 1, '', '182.72.187.97', '', '2019-05-14 02:21:15'),
(261, '95000950001557820324297', '88B1188A834E4999E053321E320AD03F', '1557820325101', '', 'VISA', '2019-05-14 13:22:00', '90.00', 'SAR', 3, 'Invalid card number', 1, 'MCP0001', '2019-05-14', '1:30 PM', 1, '', '182.72.187.97', '', '2019-05-14 02:22:55'),
(262, '95000950001557820623818', '88B67D2B389F49E5E053321E320A49B1', '1557820624579', '155782062900098476', 'VISA', '2019-05-14 13:27:00', '90.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2019-05-14', '1:30 PM', 1, '400555******0001', '182.72.187.97', '2105', '2019-05-14 02:27:54'),
(263, '95000950001557820831898', '88B11F38010749A9E053321E320A525C', '1557820832730', '155782082800098500', 'MASTERCARD', '2019-05-14 13:30:00', '90.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2019-05-14', '1:40 PM', 1, '512345******2346', '182.72.187.97', '2105', '2019-05-14 02:31:13'),
(264, '99999666661557831194567', '88B11A8982FB49E9E053321E320A0577', '1557831195477', '155783118000099593', 'VISA', '2019-05-14 16:23:00', '90.00', 'SAR', 4, 'Success', 22, 'MCP0022', '2019-05-14', '4:30 PM', 1, '400555******0001', '182.72.187.97', '2105', '2019-05-14 05:23:46'),
(265, '99999666661557831847970', '88B15F9231334991E053321E320A08B0', '1557831848801', '155783182500099664', 'VISA', '2019-05-14 16:34:00', '90.00', 'SAR', 4, 'Success', 22, 'MCP0022', '2019-05-17', '8:00 AM', 1, '400555******0001', '182.72.187.97', '2105', '2019-05-14 05:34:30'),
(266, '95000950001557898872604', '88D978970B4349DDE053321E320AD7E9', '1_9500095000_1557898872610', '', '', '2019-05-15 11:11:00', '0.00', 'SAR', 3, 'Invalid amount', 1, 'MCP0001', '2019-05-15', '1:00 PM', 1, '', '', '', '2019-05-15 00:11:15'),
(267, '95000950001557899143868', '88C47120DB6F4989E053321E320A5321', '1557899144799', '', 'VISA', '2019-05-15 11:15:00', '90.00', 'SAR', 3, 'Invalid card number', 1, 'MCP0001', '2019-05-15', '1:20 PM', 1, '', '182.72.187.97', '', '2019-05-15 00:16:23'),
(268, '95000950001557899195262', '88B10ACA944F49A1E053321E320A6E1B', '1557899196236', '155789926700002818', 'VISA', '2019-05-15 11:16:00', '90.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2019-05-15', '1:20 PM', 1, '400555******0001', '182.72.187.97', '3412', '2019-05-15 00:18:35'),
(269, '95000950001557899371187', '88C47120DB724989E053321E320A5321', '1557899372012', '155789939900002840', 'MADA', '2019-05-15 11:19:00', '90.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2019-05-15', '1:35 PM', 1, '529741******5689', '182.72.187.97', '2105', '2019-05-15 00:20:43'),
(270, '95000950001558091587950', '88ED9EC4BFFC5398E053321E320AD325', '1_9500095000_1558091587957', '', '', '2019-05-17 16:43:00', '90.00', 'SAR', 2, 'Transaction canceled by payer', 1, 'MCP0001', '2019-05-17', '5:00 PM', 1, '', '', '', '2019-05-17 05:43:29'),
(271, '95000950001558091654732', '88EDAE43C5D552C4E053321E320A9136', '1_9500095000_1558091654733', '', '', '2019-05-17 16:44:00', '90.00', 'SAR', 2, 'Transaction canceled by payer', 1, 'MCP0001', '2019-05-17', '5:00 PM', 1, '', '', '', '2019-05-17 05:44:31'),
(272, '95000950001558091685291', '88ED9EC4BFFD5398E053321E320AD325', '1_9500095000_1558091685292', '', '', '2019-05-17 16:44:00', '90.00', 'SAR', 2, 'Transaction canceled by payer', 1, 'MCP0001', '2019-05-17', '5:00 PM', 1, '', '', '', '2019-05-17 05:44:52'),
(273, '95000950001558091722689', '88FA625AD16C52A0E053321E320A3A78', '1_9500095000_1558091722691', '', '', '2019-05-17 16:45:00', '90.00', 'SAR', 2, 'Transaction canceled by payer', 1, 'MCP0001', '2019-05-17', '5:00 PM', 1, '', '', '', '2019-05-17 05:46:25'),
(274, '95000950001558097972528', '88EDABF05CD052DCE053321E320A4012', '1_9500095000_1558097972530', '', '', '2019-05-17 18:29:00', '90.00', 'SAR', 2, 'Transaction canceled by payer', 1, 'MCP0001', '2019-05-17', '6:40 PM', 1, '', '', '', '2019-05-17 07:29:41'),
(275, '95000950001558098468657', '88FA625AD17552A0E053321E320A3A78', '1558098469637', '155809859200016647', 'VISA', '2019-05-17 18:37:00', '90.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2019-05-20', '1:30 PM', 1, '400555******0001', '182.72.187.97', '2105', '2019-05-17 07:40:46'),
(276, '95000950001558098681048', '88EDBAF8C7BC536EE053321E320A36F8', '1_9500095000_1558098681049', '', '', '2019-05-17 18:41:00', '90.00', 'SAR', 2, 'Transaction canceled by payer', 1, 'MCP0001', '2019-05-17', '7:00 PM', 1, '', '', '', '2019-05-17 07:41:29'),
(277, '95000950001558098719951', '88EDBAF8C7BD536EE053321E320A36F8', '1558098721012', '155809869200016664', 'VISA', '2019-05-17 18:41:00', '90.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2019-05-17', '7:10 PM', 1, '400555******0001', '182.72.187.97', '2105', '2019-05-17 07:42:25'),
(278, '95000950001558098927030', '88FA625AD17652A0E053321E320A3A78', '1_9500095000_1558098927032', '', '', '2019-05-17 18:45:00', '900.00', 'SAR', 2, 'Transaction canceled by payer', 1, 'MCP0001', '2019-05-17', '6:44 PM', 2, '', '', '', '2019-05-17 07:45:35'),
(279, '95000950001558098954451', '88EDABF05CD752DCE053321E320A4012', '1_9500095000_1558098954452', '', '', '2019-05-17 18:45:00', '900.00', 'SAR', 2, 'Transaction canceled by payer', 1, 'MCP0001', '2019-05-17', '6:47 PM', 2, '', '', '', '2019-05-17 07:46:02'),
(280, '95000950001558424188922', '88ED98A48AB652CCE053321E320A0203', '1558424190620', '155842421700032001', 'VISA', '2019-05-21 13:06:00', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2019-05-24', '6:05 AM', 2, '400555******0001', '182.72.187.97', '2105', '2019-05-21 02:08:01'),
(281, '95000950001558424311595', '88FA625AD24152A0E053321E320A3A78', '1_9500095000_1558424311596', '', '', '2019-05-21 13:08:00', '900.00', 'SAR', 2, 'Transaction canceled by payer', 1, 'MCP0001', '2019-05-24', '6:05 AM', 2, '', '', '', '2019-05-21 02:08:42'),
(282, '95000950001558424330951', '88FA625AD24252A0E053321E320A3A78', '1558424331921', '155842429100032018', 'VISA', '2019-05-21 13:08:00', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2019-05-24', '6:05 AM', 2, '400555******0001', '182.72.187.97', '2105', '2019-05-21 02:09:14'),
(283, '95000950001558424398842', '88EDA62107B95384E053321E320A04D2', '1558424399833', '155842436500032035', 'MADA', '2019-05-21 13:09:00', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2019-05-21', '6:02 PM', 2, '529741******5689', '182.72.187.97', '2105', '2019-05-21 02:10:26'),
(284, '95000950001558425432780', '88ED8E5FC38152ECE053321E320A00C1', '1558425433761', '155842545500032186', 'VISA', '2019-05-21 13:27:00', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2019-05-21', '6:08 PM', 2, '400555******0001', '182.72.187.97', '2105', '2019-05-21 02:28:40'),
(285, '95000950001558425613087', '88ED879A7A2F52E4E053321E320AFD78', '1558425614595', '155842558300032211', 'VISA', '2019-05-21 13:30:00', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2019-05-21', '6:14 PM', 2, '400555******0001', '182.72.187.97', '2105', '2019-05-21 02:30:59'),
(286, '95000950001558426654899', '88EDBAF8C84B536EE053321E320A36F8', '1_9500095000_1558426654900', '', '', '2019-05-21 13:47:00', '90.00', 'SAR', 2, 'Transaction canceled by payer', 1, 'MCP0001', '2019-05-21', '1:55 PM', 1, '', '', '', '2019-05-21 02:48:18'),
(287, '95000950001558426972386', '88EDAED8AC6A538CE053321E320AE035', '1558426973450', '155842693700032335', 'VISA', '2019-05-21 13:52:00', '90.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2019-05-21', '2:05 PM', 1, '400555******0001', '182.72.187.97', '2105', '2019-05-21 02:53:21'),
(288, '74182612831558520553793', '88EDFD598EAE534CE053321E320AEF3F', '1558520555438', '155852052600039891', 'VISA', '2019-05-22 15:52:00', '1.00', 'SAR', 4, 'Success', 2, 'MCP0002', '2019-05-22', '4:05 PM', 1, '400555******0001', '182.72.187.97', '2105', '2019-05-22 04:53:13'),
(289, '86084386901558520553581', '8929E6B9BAEF5298E053321E320A363E', '1558520554973', '155852052600039893', 'VISA', '2019-05-22 15:52:00', '1.00', 'SAR', 4, 'Success', 25, 'MCP0025', '2019-05-22', '4:05 PM', 1, '400555******0001', '182.72.187.97', '2105', '2019-05-22 04:53:14'),
(290, '86084386901558521340310', '88EDA62108CF5384E053321E320A04D2', '1558521341301', '155852130400040007', 'VISA', '2019-05-22 16:05:00', '100.00', 'SAR', 4, 'Success', 25, 'MCP0025', '2019-05-22', '4:12 PM', 25, '400555******0001', '182.72.187.97', '2105', '2019-05-22 05:06:12'),
(291, '86084386901558521768146', '88EDFD598EB8534CE053321E320AEF3F', '1558521769193', '155852174200040113', 'VISA', '2019-05-22 16:12:00', '1.00', 'SAR', 4, 'Success', 25, 'MCP0025', '2019-05-22', '4:17 PM', 1, '400555******0001', '182.72.187.97', '2105', '2019-05-22 05:13:29'),
(292, '74182612831558521768460', '88EDD29F740652B4E053321E320A64DB', '1558521769849', '155852175800040118', 'VISA', '2019-05-22 16:12:00', '1.00', 'SAR', 4, 'Success', 2, 'MCP0002', '2019-05-22', '4:17 PM', 1, '400555******0001', '182.72.187.97', '2105', '2019-05-22 05:13:45'),
(293, '74182612831558675754522', '88EDABF05F9E52DCE053321E320A4012', '1558675755722', '155867571000050689', 'VISA', '2019-05-24 10:59:00', '200.00', 'SAR', 4, 'Success', 2, 'MCP0002', '2019-05-27', '1:36 PM', 1, '400555******0001', '182.72.187.97', '2105', '2019-05-23 23:59:43'),
(294, '74182612831558675877302', '8928F6E7CA735346E053321E320AD27A', '1558675878612', '155867582400050692', 'VISA', '2019-05-24 11:01:00', '200.00', 'SAR', 4, 'Success', 2, 'MCP0002', '2019-05-27', '1:42 PM', 1, '400555******0001', '182.72.187.97', '2105', '2019-05-24 00:01:37'),
(295, '95000950001558932655339', '88ED8E5FC46A52ECE053321E320A00C1', '1558932656485', '155893260100061530', 'VISA', '2019-05-27 10:20:00', '200.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2019-05-27', '10:54 AM', 1, '400555******0001', '182.72.187.97', '2105', '2019-05-26 23:21:22'),
(296, '74182612831558932943133', '88EDA6210B095384E053321E320A04D2', '1558932944566', '155893289200061543', 'VISA', '2019-05-27 10:25:00', '200.00', 'SAR', 4, 'Success', 2, 'MCP0002', '2019-05-27', '10:30 AM', 1, '400555******0001', '182.72.187.97', '2105', '2019-05-26 23:26:12'),
(297, '74182612831558934680441', '88ED8E5FC47752ECE053321E320A00C1', '1558934690881', '155893463700061655', 'VISA', '2019-05-27 10:54:00', '200.00', 'SAR', 4, 'Success', 2, 'MCP0002', '2019-05-30', '9:06 AM', 1, '400555******0001', '182.72.187.97', '2105', '2019-05-26 23:55:23'),
(298, '74182612831558940601809', '88ED8E5FC48A52ECE053321E320A00C1', '1558940602958', '155894054700062170', 'VISA', '2019-05-27 12:33:00', '200.00', 'SAR', 4, 'Success', 2, 'MCP0002', '2019-05-30', '9:00 AM', 1, '400555******0001', '182.72.187.97', '2105', '2019-05-27 01:33:48'),
(299, '95000950001558940653023', '8928F6E7CB795346E053321E320AD27A', '1558940654024', '155894059500062183', 'VISA', '2019-05-27 12:34:00', '200.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2019-05-30', '9:00 AM', 1, '400555******0001', '182.72.187.97', '2105', '2019-05-27 01:34:36'),
(300, '95000950001559201330629', '8A0878734C575ECFE053321E320A73B0', '1559201331825', '155920125900080473', 'VISA', '2019-05-30 12:58:00', '200.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2019-06-02', '2:00 PM', 1, '400555******0001', '182.72.187.97', '2105', '2019-05-30 01:59:08'),
(301, '1559230068188', '8A19C24CD05E0C87E053321E320AAEA3', '', '', 'VISA', '2018-03-02 12:30:00', '50.00', 'SAR', 2, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '', '', '', '2019-05-30 09:58:18'),
(302, '1559230104443', '8A19B0C5F78B01FEE053321E320A6E69', '', '', 'VISA', '2018-03-02 12:30:00', '50.00', 'SAR', 2, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '', '', '', '2019-05-30 10:01:24'),
(303, '1559232013495', '8A19E299A4BC01FAE053321E320A3EEB', '', '', '', '2018-03-02 12:30:00', '50.00', 'SAR', 3, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '', '', '', '2019-05-30 10:30:17'),
(304, '1559232044603', '8A19E299A4BE01FAE053321E320A3EEB', '', '', '', '2018-03-02 12:30:00', '50.00', 'SAR', 3, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '', '', '', '2019-05-30 10:30:48'),
(305, '1559232293993', '8A19C24CD0650C87E053321E320AAEA3', '1559232293993', '155923227100083482', 'VISA', '2019-05-30 21:34:56', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '182.72.187.97', '3412', '2019-05-30 10:36:00'),
(306, '1559233151527', '8A19C24CD0680C87E053321E320AAEA3', '', '', '', '2018-03-02 12:30:00', '50.00', 'SAR', 3, 'Success', 17, 'MCP0017', '2018-03-02', '2:00 PM', 1, '', '', '', '2019-05-30 10:49:17'),
(307, '1559233526164', '8A19C24CD0690C87E053321E320AAEA3', '1559233526164', '155923347000083496', 'VISA', '2019-05-30 21:55:27', '100.00', 'SAR', 4, 'Success', 17, 'MCP0017', '2018-03-02', '2:00 PM', 1, '400555******0001', '182.72.187.97', '2312', '2019-05-30 10:55:59'),
(308, '74182612831559233750734', '8A19AC8E14D90C83E053321E320AE645', '1559233752018', '155923369100083505', 'VISA', '2019-05-30 21:59:00', '100.00', 'SAR', 4, 'Success', 17, 'MCP0017', '2019-05-31', '8:15 AM', 1, '400555******0001', '182.72.187.97', '3412', '2019-05-30 10:59:42'),
(309, '1559296988497', '8A199EADA2990206E053321E320A372E', '', '', 'VISA', '2018-03-02 12:30:00', '50.00', 'SAR', 2, 'Success', 17, 'MCP0017', '2018-03-02', '2:00 PM', 2, '', '', '', '2019-05-31 04:33:17'),
(310, '1559298558454', '8A199EADA2A10206E053321E320A372E', '1559298558454', '155929853100086468', 'VISA', '2019-05-31 15:59:19', '500.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 21, '400555******0001', '182.72.187.97', '2105', '2019-05-31 05:00:22'),
(311, '1559299584662', '8A19AC8E155E0C83E053321E320AE645', '1559299584662', '155929952700086527', 'VISA', '2019-05-31 16:16:26', '900.00', 'SAR', 4, 'Success', 33, 'MCP0033', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.72.187.97', '2105', '2019-05-31 05:16:58'),
(312, '1559299831287', '8A19AC8E15610C83E053321E320AE645', '', '', 'VISA', '2018-03-02 12:30:00', '50.00', 'SAR', 2, 'Success', 33, 'MCP0033', '2018-03-02', '2:00 PM', 2, '', '', '', '2019-05-31 05:20:42'),
(313, '1559307141328', '8A2E6D3C73DB0C77E053321E320A414E', '1559307141328', '155930707600087195', 'VISA', '2019-05-31 18:22:23', '500.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 21, '400555******0001', '182.72.187.97', '2105', '2019-05-31 07:22:48'),
(314, '1559308773395', '8A199EADA2CE0206E053321E320A372E', '1559308773395', '155930870700087254', 'VISA', '2019-05-31 18:49:35', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '182.72.187.97', '2105', '2019-05-31 07:49:59'),
(315, '1559309391520', '8A199EADA2D30206E053321E320A372E', '', '', 'VISA', '2018-03-02 12:30:00', '50.00', 'SAR', 2, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '', '', '', '2019-05-31 08:03:43'),
(316, '1559363858871', '8A199EADA3010206E053321E320A372E', '1559363858871', '155936380100089176', 'VISA', '2019-06-01 10:07:41', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '182.72.187.97', '2105', '2019-05-31 23:08:15'),
(317, '1559363938363', '8A2E6D3C74040C77E053321E320A414E', '1559363938363', '155936386800089178', 'VISA', '2019-06-01 10:09:00', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '182.72.187.97', '2105', '2019-05-31 23:09:21'),
(318, '1559363987683', '8A19E299A53101FAE053321E320A3EEB', '1559363987683', '155936392300089181', 'VISA', '2019-06-01 10:09:49', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '182.72.187.97', '2105', '2019-05-31 23:10:16'),
(319, '1559364094943', '8A2E6D3C74050C77E053321E320A414E', '1559364094943', '155936402400089185', 'VISA', '2019-06-01 10:11:36', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '182.72.187.97', '2105', '2019-05-31 23:11:57'),
(320, '1559364146196', '8A2E6D3C74060C77E053321E320A414E', '1559364146196', '155936407400089187', 'VISA', '2019-06-01 10:12:27', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '182.72.187.97', '2105', '2019-05-31 23:12:46'),
(321, '1559364221771', '8A2E6D3C74080C77E053321E320A414E', '1559364221771', '155936415300089192', 'VISA', '2019-06-01 10:13:43', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.72.187.97', '2105', '2019-05-31 23:14:06'),
(322, '1559364343951', '8A19E299A53201FAE053321E320A3EEB', '1559364343951', '155936427300089194', 'VISA', '2019-06-01 10:15:45', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '182.72.187.97', '2105', '2019-05-31 23:16:06'),
(323, '1559366987527', '8A2E6D3C74090C77E053321E320A414E', '1559366987527', '155936692300089273', 'VISA', '2019-06-01 10:59:49', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '182.72.187.97', '2105', '2019-06-01 00:00:17'),
(324, '1559367054035', '8A2E6D3C740A0C77E053321E320A414E', '1559367054035', '155936700900089275', 'VISA', '2019-06-01 11:00:55', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '182.72.187.97', '2105', '2019-06-01 00:01:42'),
(325, '1559369762642', '8A199EADA30B0206E053321E320A372E', '1559369762642', '155936969300089386', 'VISA', '2019-06-01 11:46:04', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '103.16.202.6', '2105', '2019-06-01 00:46:26'),
(326, '1559369924639', '8A19AC8E15920C83E053321E320AE645', '1559369924639', '155936986300089410', 'VISA', '2019-06-01 11:48:46', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '103.16.202.6', '2105', '2019-06-01 00:49:19'),
(327, '1559370100771', '8A2B875D61500C7BE053321E320AA36C', '1559370100771', '155937003100089435', 'VISA', '2019-06-01 11:51:42', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '103.16.202.6', '2105', '2019-06-01 00:52:05'),
(328, '1559370570768', '8A2B4F7D0FF601F6E053321E320ABDE6', '1559370570768', '155937051800089481', 'VISA', '2019-06-01 11:59:32', '100.00', 'SAR', 4, 'Success', 25, 'MCP0025', '2018-03-02', '2:00 PM', 1, '400555******0001', '103.16.202.6', '2105', '2019-06-01 01:00:12'),
(329, '1559373416314', '8A2B875D61550C7BE053321E320AA36C', '1559373416314', '155937334700089532', 'VISA', '2019-06-01 12:46:58', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '103.16.202.6', '2105', '2019-06-01 01:47:22'),
(330, '1559377599755', '8A2E6D3C74160C77E053321E320A414E', '1559377599755', '155937753200089670', 'VISA', '2019-06-01 13:56:41', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '103.16.202.6', '2105', '2019-06-01 02:57:06'),
(331, '1559382838724', '8A2B875D615C0C7BE053321E320AA36C', '1559382838724', '155938277400089934', 'VISA', '2019-06-01 15:24:00', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '103.16.202.6', '2105', '2019-06-01 04:24:28'),
(332, '1559382915657', '8A2B875D615D0C7BE053321E320AA36C', '1559382915657', '155938285400089937', 'VISA', '2019-06-01 15:25:17', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '103.16.202.6', '2105', '2019-06-01 04:25:56'),
(333, '1559382991233', '8A19AC8E15A10C83E053321E320AE645', '1559382991233', '155938293000089939', 'VISA', '2019-06-01 15:26:32', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '103.16.202.6', '2105', '2019-06-01 04:27:03'),
(334, '1559385144620', '8A19AC8E15AA0C83E053321E320AE645', '1559385144620', '155938508200089980', 'VISA', '2019-06-01 16:02:26', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '103.16.202.6', '2105', '2019-06-01 05:02:57'),
(335, '1559385331454', '8A2B4F7D100501F6E053321E320ABDE6', '1559385331454', '155938526300089985', 'VISA', '2019-06-01 16:05:33', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '103.16.202.6', '2105', '2019-06-01 05:05:57'),
(336, '1559385752317', '8A2E6D3C741A0C77E053321E320A414E', '1559385752317', '155938568300089993', 'VISA', '2019-06-01 16:12:34', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '103.16.202.6', '2105', '2019-06-01 05:12:57'),
(337, '1559390871795', '8A2B875D61670C7BE053321E320AA36C', '1559390871795', '155939080900090238', 'VISA', '2019-06-01 17:37:53', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '103.16.202.6', '2105', '2019-06-01 06:38:24'),
(338, '1559391156889', '8A199CB820850202E053321E320A0F92', '1559391156889', '155939109100090266', 'VISA', '2019-06-01 17:42:38', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '103.16.202.6', '2105', '2019-06-01 06:43:04'),
(339, '1559391950253', '8A199CB820890202E053321E320A0F92', '1559391950253', '155939188500090370', 'VISA', '2019-06-01 17:55:51', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '103.16.202.6', '2105', '2019-06-01 06:56:19'),
(340, '1559392050695', '8A199CB8208B0202E053321E320A0F92', '1559392050695', '155939198100090390', 'VISA', '2019-06-01 17:57:32', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '103.16.202.6', '2105', '2019-06-01 06:57:55'),
(341, '1559392210611', '8A199CB8208D0202E053321E320A0F92', '1559392210611', '155939214500090411', 'VISA', '2019-06-01 18:00:12', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '103.16.202.6', '2105', '2019-06-01 07:00:39'),
(342, '1559396179647', '8A199CB820950202E053321E320A0F92', '1559396179647', '155939611300090498', 'VISA', '2019-06-01 19:06:21', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '103.16.202.6', '2105', '2019-06-01 08:06:48'),
(343, '1559396267123', '8A19B0C5F7E901FEE053321E320A6E69', '1559396267123', '155939619900090500', 'VISA', '2019-06-01 19:07:48', '100.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '103.16.202.6', '2105', '2019-06-01 08:08:14'),
(344, '1559549013009', '8A56C714BE6F6B35E053321E320A16AD', '1559549013009', '155954893900096283', 'VISA', '2019-06-03 13:33:35', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '103.16.202.6', '2105', '2019-06-03 02:33:59'),
(345, '1559554612333', '8A56C714BE7B6B35E053321E320A16AD', '1559554612333', '155955455300096835', 'VISA', '2019-06-03 15:06:54', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '400555******0001', '103.16.202.6', '2105', '2019-06-03 04:07:33'),
(346, '1559556487684', '8A5585B263AD6B31E053321E320A36C6', '1559556487684', '155955641900097010', 'VISA', '2019-06-03 15:38:09', '50.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '103.16.202.6', '2105', '2019-06-03 04:38:39'),
(347, '1559556807527', '8A559AA524A86A61E053321E320A1E78', '1559556807527', '155955673000097032', 'VISA', '2019-06-03 15:43:29', '50.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '103.16.202.6', '2105', '2019-06-03 04:43:50'),
(348, '1559556912971', '8A56EEF17B716A59E053321E320ABC90', '1559556912971', '155955683600097040', 'VISA', '2019-06-03 15:45:14', '50.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '103.16.202.6', '2105', '2019-06-03 04:45:35'),
(349, '1559557227572', '8A562558E81A6A6BE053321E320A811A', '1559557227572', '155955715000097092', 'VISA', '2019-06-03 15:50:29', '50.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '103.16.202.6', '2105', '2019-06-03 04:50:50'),
(350, '1559557298259', '8A559AA500D16B29E053321E320AFCA4', '1559557298259', '155955722200097105', 'VISA', '2019-06-03 15:51:40', '50.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '103.16.202.6', '2105', '2019-06-03 04:52:02'),
(351, '1559557639294', '8A56EEF17B776A59E053321E320ABC90', '1559557639294', '155955756200097153', 'VISA', '2019-06-03 15:57:20', '501.00', 'SAR', 4, 'Success', 25, 'MCP0025', '2018-03-02', '2:00 PM', 21, '400555******0001', '103.16.202.6', '2105', '2019-06-03 04:57:42'),
(352, '1559621887028', '8A7045A7E6E76A49E053321E320A8923', '1559621887028', '155962181000000299', 'VISA', '2019-06-04 09:48:08', '50.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '103.16.202.6', '2105', '2019-06-03 22:48:34'),
(353, '1559622137216', '8A706531892B6AE1E053321E320ACB76', '1559622137216', '155962208400000304', 'MASTERCARD', '2019-06-04 09:52:19', '900.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 2, '512345******2346', '103.16.202.6', '2105', '2019-06-03 22:53:07'),
(354, '1559625716487', '8A56C714BF9C6B35E053321E320A16AD', '1559625716487', '155962564000000386', 'VISA', '2019-06-04 10:51:58', '50.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '103.16.202.6', '2105', '2019-06-03 23:52:24'),
(355, '1559625793745', '8A706508C9446AD9E053321E320AF209', '1559625793745', '155962575100000388', 'VISA', '2019-06-04 10:53:16', '50.00', 'SAR', 4, 'Success', 1, 'MCP0001', '2018-03-02', '2:00 PM', 1, '400555******0001', '103.16.202.6', '2102', '2019-06-03 23:54:13');

-- --------------------------------------------------------

--
-- Table structure for table `user_account`
--

CREATE TABLE `user_account` (
  `user_account_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `username` varchar(100) CHARACTER SET utf8 NOT NULL,
  `password` varchar(100) CHARACTER SET utf8 NOT NULL,
  `user_type` int(11) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_account`
--

INSERT INTO `user_account` (`user_account_id`, `user_id`, `username`, `password`, `user_type`, `created_date`) VALUES
(1, 1, 'ram@gmail.com', '827ccb0eea8a706c4c34a16891f84e7b', 1, '2019-02-06 10:56:19'),
(2, 1, '+919500095000', '827ccb0eea8a706c4c34a16891f84e7b', 2, '2018-12-19 04:52:16'),
(3, 3, 'siva@gmail.com', '827ccb0eea8a706c4c34a16891f84e7b', 1, '2019-02-06 10:56:13');

-- --------------------------------------------------------

--
-- Table structure for table `version_tracking`
--

CREATE TABLE `version_tracking` (
  `trackingId` int(11) NOT NULL,
  `versionNumber` varchar(33) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `deviceType` enum('0','1','2','3','4') NOT NULL DEFAULT '0',
  `userId` int(11) NOT NULL DEFAULT 0,
  `userType` enum('0','1','2','3','4') NOT NULL DEFAULT '0',
  `login_date` datetime NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `version_tracking`
--

INSERT INTO `version_tracking` (`trackingId`, `versionNumber`, `deviceType`, `userId`, `userType`, `login_date`, `created_date`) VALUES
(1, 'v1.1', '1', 1, '1', '2019-02-13 11:02:37', '2019-02-13 08:02:23');

-- --------------------------------------------------------

--
-- Table structure for table `welcome_offer`
--

CREATE TABLE `welcome_offer` (
  `offer_id` int(11) NOT NULL,
  `offer_appoinments_count` int(11) NOT NULL DEFAULT 0,
  `offer_validity_days` int(11) NOT NULL DEFAULT 0,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `welcome_offer`
--

INSERT INTO `welcome_offer` (`offer_id`, `offer_appoinments_count`, `offer_validity_days`, `created_date`, `updated_date`) VALUES
(1, 0, 0, '2018-10-26 14:40:37', '2019-01-08 18:30:00');

-- --------------------------------------------------------

--
-- Table structure for table `welcome_offer_tracking`
--

CREATE TABLE `welcome_offer_tracking` (
  `offer_tracking_id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL DEFAULT 0,
  `free_appointments` int(5) NOT NULL DEFAULT 0,
  `expiry_date` varchar(22) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `total_appointments` int(5) NOT NULL DEFAULT 0,
  `appointment_ids` varchar(33) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `created_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`admin_id`);

--
-- Indexes for table `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`appointment_id`);

--
-- Indexes for table `appointment_call_status`
--
ALTER TABLE `appointment_call_status`
  ADD PRIMARY KEY (`appointment_call_status_id`);

--
-- Indexes for table `appointment_slot_block`
--
ALTER TABLE `appointment_slot_block`
  ADD PRIMARY KEY (`appointment_block_id`);

--
-- Indexes for table `claim`
--
ALTER TABLE `claim`
  ADD PRIMARY KEY (`claim_id`);

--
-- Indexes for table `claim_configuration`
--
ALTER TABLE `claim_configuration`
  ADD PRIMARY KEY (`claim_config_id`);

--
-- Indexes for table `claim_options`
--
ALTER TABLE `claim_options`
  ADD PRIMARY KEY (`claim_option_id`);

--
-- Indexes for table `dependent`
--
ALTER TABLE `dependent`
  ADD PRIMARY KEY (`dependent_id`);

--
-- Indexes for table `dependent_max_count`
--
ALTER TABLE `dependent_max_count`
  ADD PRIMARY KEY (`dependent_max_id`);

--
-- Indexes for table `dependent_type`
--
ALTER TABLE `dependent_type`
  ADD PRIMARY KEY (`dependent_type_id`);

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`doctor_id`);

--
-- Indexes for table `doctor_bank_details`
--
ALTER TABLE `doctor_bank_details`
  ADD PRIMARY KEY (`doctor_bank_id`);

--
-- Indexes for table `doctor_clinic_details`
--
ALTER TABLE `doctor_clinic_details`
  ADD PRIMARY KEY (`doctor_clinic_id`);

--
-- Indexes for table `doctor_consultaion`
--
ALTER TABLE `doctor_consultaion`
  ADD PRIMARY KEY (`doctor_consultation_id`);

--
-- Indexes for table `doctor_consultaion_timing`
--
ALTER TABLE `doctor_consultaion_timing`
  ADD PRIMARY KEY (`doctor_consult_time_id`);

--
-- Indexes for table `doctor_education`
--
ALTER TABLE `doctor_education`
  ADD PRIMARY KEY (`education_id`);

--
-- Indexes for table `doctor_experience`
--
ALTER TABLE `doctor_experience`
  ADD PRIMARY KEY (`experience_id`);

--
-- Indexes for table `doctor_payment`
--
ALTER TABLE `doctor_payment`
  ADD PRIMARY KEY (`doctor_payment_id`);

--
-- Indexes for table `doctor_payment_transaction`
--
ALTER TABLE `doctor_payment_transaction`
  ADD PRIMARY KEY (`payment_transaction_id`);

--
-- Indexes for table `fcm_device_token`
--
ALTER TABLE `fcm_device_token`
  ADD PRIMARY KEY (`fcmId`);

--
-- Indexes for table `follower`
--
ALTER TABLE `follower`
  ADD PRIMARY KEY (`follower_id`),
  ADD UNIQUE KEY `follower_id` (`follower_id`);

--
-- Indexes for table `language`
--
ALTER TABLE `language`
  ADD PRIMARY KEY (`phrase_id`);

--
-- Indexes for table `language_known`
--
ALTER TABLE `language_known`
  ADD PRIMARY KEY (`language_known_id`);

--
-- Indexes for table `maya_commission`
--
ALTER TABLE `maya_commission`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `medical_history`
--
ALTER TABLE `medical_history`
  ADD PRIMARY KEY (`medical_historyId`);

--
-- Indexes for table `medical_questions`
--
ALTER TABLE `medical_questions`
  ADD PRIMARY KEY (`medical_questions_id`);

--
-- Indexes for table `medical_questions_type`
--
ALTER TABLE `medical_questions_type`
  ADD PRIMARY KEY (`medical_questions_type_id`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`patient_id`);

--
-- Indexes for table `patient_wallet`
--
ALTER TABLE `patient_wallet`
  ADD PRIMARY KEY (`wallet_id`);

--
-- Indexes for table `patient_wallet_transaction`
--
ALTER TABLE `patient_wallet_transaction`
  ADD PRIMARY KEY (`wallet_transaction_id`);

--
-- Indexes for table `payfort_response`
--
ALTER TABLE `payfort_response`
  ADD PRIMARY KEY (`response_id`);

--
-- Indexes for table `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`post_id`);

--
-- Indexes for table `post_like`
--
ALTER TABLE `post_like`
  ADD PRIMARY KEY (`like_id`);

--
-- Indexes for table `promotion`
--
ALTER TABLE `promotion`
  ADD PRIMARY KEY (`promotion_id`);

--
-- Indexes for table `promotion_tracking`
--
ALTER TABLE `promotion_tracking`
  ADD PRIMARY KEY (`promotion_tracking_id`);

--
-- Indexes for table `push_notification_data`
--
ALTER TABLE `push_notification_data`
  ADD PRIMARY KEY (`notification_id`);

--
-- Indexes for table `settings`
--
ALTER TABLE `settings`
  ADD PRIMARY KEY (`settings_id`);

--
-- Indexes for table `specialization`
--
ALTER TABLE `specialization`
  ADD PRIMARY KEY (`specialization_id`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`transaction_id`);

--
-- Indexes for table `user_account`
--
ALTER TABLE `user_account`
  ADD PRIMARY KEY (`user_account_id`);

--
-- Indexes for table `version_tracking`
--
ALTER TABLE `version_tracking`
  ADD PRIMARY KEY (`trackingId`);

--
-- Indexes for table `welcome_offer`
--
ALTER TABLE `welcome_offer`
  ADD PRIMARY KEY (`offer_id`);

--
-- Indexes for table `welcome_offer_tracking`
--
ALTER TABLE `welcome_offer_tracking`
  ADD PRIMARY KEY (`offer_tracking_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `admin_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `appointment`
--
ALTER TABLE `appointment`
  MODIFY `appointment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `appointment_call_status`
--
ALTER TABLE `appointment_call_status`
  MODIFY `appointment_call_status_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `appointment_slot_block`
--
ALTER TABLE `appointment_slot_block`
  MODIFY `appointment_block_id` int(22) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `claim`
--
ALTER TABLE `claim`
  MODIFY `claim_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `claim_configuration`
--
ALTER TABLE `claim_configuration`
  MODIFY `claim_config_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `claim_options`
--
ALTER TABLE `claim_options`
  MODIFY `claim_option_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `dependent`
--
ALTER TABLE `dependent`
  MODIFY `dependent_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `dependent_max_count`
--
ALTER TABLE `dependent_max_count`
  MODIFY `dependent_max_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `dependent_type`
--
ALTER TABLE `dependent_type`
  MODIFY `dependent_type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `doctor`
--
ALTER TABLE `doctor`
  MODIFY `doctor_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=211;

--
-- AUTO_INCREMENT for table `doctor_bank_details`
--
ALTER TABLE `doctor_bank_details`
  MODIFY `doctor_bank_id` int(22) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `doctor_clinic_details`
--
ALTER TABLE `doctor_clinic_details`
  MODIFY `doctor_clinic_id` int(22) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `doctor_consultaion`
--
ALTER TABLE `doctor_consultaion`
  MODIFY `doctor_consultation_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `doctor_consultaion_timing`
--
ALTER TABLE `doctor_consultaion_timing`
  MODIFY `doctor_consult_time_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `doctor_education`
--
ALTER TABLE `doctor_education`
  MODIFY `education_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `doctor_experience`
--
ALTER TABLE `doctor_experience`
  MODIFY `experience_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `doctor_payment`
--
ALTER TABLE `doctor_payment`
  MODIFY `doctor_payment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `doctor_payment_transaction`
--
ALTER TABLE `doctor_payment_transaction`
  MODIFY `payment_transaction_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `fcm_device_token`
--
ALTER TABLE `fcm_device_token`
  MODIFY `fcmId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `follower`
--
ALTER TABLE `follower`
  MODIFY `follower_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `language`
--
ALTER TABLE `language`
  MODIFY `phrase_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=234;

--
-- AUTO_INCREMENT for table `language_known`
--
ALTER TABLE `language_known`
  MODIFY `language_known_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `maya_commission`
--
ALTER TABLE `maya_commission`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `medical_history`
--
ALTER TABLE `medical_history`
  MODIFY `medical_historyId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `medical_questions`
--
ALTER TABLE `medical_questions`
  MODIFY `medical_questions_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `medical_questions_type`
--
ALTER TABLE `medical_questions_type`
  MODIFY `medical_questions_type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
  MODIFY `patient_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `patient_wallet`
--
ALTER TABLE `patient_wallet`
  MODIFY `wallet_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `patient_wallet_transaction`
--
ALTER TABLE `patient_wallet_transaction`
  MODIFY `wallet_transaction_id` int(22) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `payfort_response`
--
ALTER TABLE `payfort_response`
  MODIFY `response_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `post`
--
ALTER TABLE `post`
  MODIFY `post_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `post_like`
--
ALTER TABLE `post_like`
  MODIFY `like_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `promotion`
--
ALTER TABLE `promotion`
  MODIFY `promotion_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `promotion_tracking`
--
ALTER TABLE `promotion_tracking`
  MODIFY `promotion_tracking_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `push_notification_data`
--
ALTER TABLE `push_notification_data`
  MODIFY `notification_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `settings`
--
ALTER TABLE `settings`
  MODIFY `settings_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `specialization`
--
ALTER TABLE `specialization`
  MODIFY `specialization_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `transaction_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=356;

--
-- AUTO_INCREMENT for table `user_account`
--
ALTER TABLE `user_account`
  MODIFY `user_account_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `version_tracking`
--
ALTER TABLE `version_tracking`
  MODIFY `trackingId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `welcome_offer`
--
ALTER TABLE `welcome_offer`
  MODIFY `offer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `welcome_offer_tracking`
--
ALTER TABLE `welcome_offer_tracking`
  MODIFY `offer_tracking_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Database: `spring_security_sample`
--
CREATE DATABASE IF NOT EXISTS `spring_security_sample` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `spring_security_sample`;

-- --------------------------------------------------------

--
-- Table structure for table `articles`
--

CREATE TABLE `articles` (
  `id` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_role` int(11) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `user_id` int(11) NOT NULL,
  `username` varchar(120) NOT NULL,
  `password` varchar(256) NOT NULL,
  `email` varchar(120) NOT NULL,
  `user_type` int(2) NOT NULL,
  `user_role` int(2) NOT NULL,
  `company_name` varchar(120) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`user_id`, `username`, `password`, `email`, `user_type`, `user_role`, `company_name`, `status`) VALUES
(2, 'client8', '0hmCKKFlbtY5BKDvdCZjBg==', 'client@client.com8', 1, 1, 'Client pvt ltd8', 20),
(3, 'client', '0hmCKKFlbtY5BKDvdCZjBg==', 'client@client.com', 1, 1, 'Client pvt ltd8', 20),
(4, 'client2323e', '0hmCKKFlbtY5BKDvdCZjBg==', 'client@client.com2e32', 1, 1, 'Client pvt ltd8', 20),
(5, 'client5', '0hmCKKFlbtY5BKDvdCZjBg==', 'client@client.com5', 1, 1, 'Client pvt ltd5', 20),
(6, 'clds_client', '0hmCKKFlbtY5BKDvdCZjBg==', 'client@clds.com', 1, 1, 'CLDS', 20),
(10, 'client10', '0hmCKKFlbtY5BKDvdCZjBg==', 'client@clds.com10', 1, 1, 'CD10', 20),
(11, 'prakash', '0hmCKKFlbtY5BKDvdCZjBg==', 'prakash@clds.com', 1, 1, 'CD13', 20),
(12, 'user', '0hmCKKFlbtY5BKDvdCZjBg==', 'user@clds.com', 1, 1, 'CD13', 20);

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_role` int(11) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_account`
--

CREATE TABLE `user_account` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `username` varchar(120) NOT NULL,
  `password` varchar(256) NOT NULL,
  `user_type` int(11) NOT NULL,
  `user_role` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_account`
--

INSERT INTO `user_account` (`id`, `user_id`, `username`, `password`, `user_type`, `user_role`, `status`, `created_date`) VALUES
(1, 2, 'client8', '0hmCKKFlbtY5BKDvdCZjBg==', 1, 1, 20, '2019-09-26 09:25:05'),
(2, 3, 'client', '0hmCKKFlbtY5BKDvdCZjBg==', 1, 1, 20, '2019-09-26 09:34:41'),
(3, 4, 'client2323e', '0hmCKKFlbtY5BKDvdCZjBg==', 1, 1, 20, '2019-09-26 09:53:48'),
(4, 5, 'client5', '0hmCKKFlbtY5BKDvdCZjBg==', 1, 1, 20, '2019-09-26 10:48:44'),
(5, 6, 'clds_client', '0hmCKKFlbtY5BKDvdCZjBg==', 1, 1, 20, '2019-09-26 10:49:30'),
(6, 10, 'client10', '0hmCKKFlbtY5BKDvdCZjBg==', 1, 1, 20, '2019-09-26 10:54:44'),
(7, 11, 'prakash', '0hmCKKFlbtY5BKDvdCZjBg==', 1, 1, 20, '2019-09-26 12:27:55'),
(8, 12, 'user', '0hmCKKFlbtY5BKDvdCZjBg==', 1, 1, 20, '2019-09-26 12:27:55');

-- --------------------------------------------------------

--
-- Table structure for table `user_cookie`
--

CREATE TABLE `user_cookie` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `user_type` int(11) NOT NULL,
  `login_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `cookie` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_cookie`
--

INSERT INTO `user_cookie` (`id`, `user_id`, `user_type`, `login_time`, `cookie`) VALUES
(1, 0, 1, '2019-09-27 11:57:01', 'javax.servlet.http.Cookie@11ef9ad8'),
(2, 0, 1, '2019-09-27 11:58:01', 'javax.servlet.http.Cookie@44341791'),
(3, 0, 1, '2019-09-27 12:00:34', 'javax.servlet.http.Cookie@2af9d48c'),
(4, 0, 1, '2019-09-27 12:12:12', 'javax.servlet.http.Cookie@2b87aeb'),
(5, 0, 1, '2019-09-27 12:12:22', 'javax.servlet.http.Cookie@d76ced9'),
(6, 7, 1, '2019-09-27 12:15:35', 'javax.servlet.http.Cookie@3774e5be'),
(7, 7, 1, '2019-09-27 12:15:38', 'javax.servlet.http.Cookie@38a23569'),
(8, 7, 1, '2019-09-27 12:23:11', 'javax.servlet.http.Cookie@6a5b2d75'),
(9, 7, 1, '2019-09-27 12:23:16', 'javax.servlet.http.Cookie@40ca96d6'),
(10, 7, 1, '2019-09-27 12:33:54', 'javax.servlet.http.Cookie@7b88674d'),
(11, 7, 1, '2019-09-27 12:33:56', 'javax.servlet.http.Cookie@629cc042'),
(12, 7, 1, '2019-09-27 12:34:13', 'javax.servlet.http.Cookie@65cc1a4b'),
(13, 11, 1, '2019-09-27 12:36:07', 'javax.servlet.http.Cookie@66d8acbf');

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE `user_roles` (
  `user_id` int(11) NOT NULL,
  `roles` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `articles`
--
ALTER TABLE `articles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  ADD UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`);

--
-- Indexes for table `user_account`
--
ALTER TABLE `user_account`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_cookie`
--
ALTER TABLE `user_cookie`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD KEY `FK55itppkw3i07do3h7qoclqd4k` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user_account`
--
ALTER TABLE `user_account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `user_cookie`
--
ALTER TABLE `user_cookie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- Database: `test`
--
CREATE DATABASE IF NOT EXISTS `test` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `test`;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
