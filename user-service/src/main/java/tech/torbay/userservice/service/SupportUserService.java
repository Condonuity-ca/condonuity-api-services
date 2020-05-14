package tech.torbay.userservice.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import tech.torbay.userservice.repository.CommentFilesRepository;
import tech.torbay.userservice.repository.ExternalMessageCommentRepository;
import tech.torbay.userservice.repository.ExternalMessageRepository;
import tech.torbay.userservice.repository.InternalMessageCommentRepository;
import tech.torbay.userservice.repository.InternalMessageRepository;
import tech.torbay.userservice.repository.ThreadFilesRepository;
import tech.torbay.userservice.entity.ExternalMessage;
import tech.torbay.userservice.entity.ExternalMessageComment;
import tech.torbay.userservice.repository.VendorBidRepository;
import tech.torbay.userservice.repository.VendorProjectInterestsRepository;
import tech.torbay.userservice.Utils.Utils;
import tech.torbay.userservice.constants.Constants.ThreadType;
import tech.torbay.userservice.entity.CommentFiles;
import tech.torbay.userservice.entity.InternalMessage;
import tech.torbay.userservice.entity.InternalMessageComment;
import tech.torbay.userservice.entity.ThreadFiles;
import tech.torbay.userservice.constants.Constants;
import tech.torbay.userservice.entity.ClientBuildingRepository;
import tech.torbay.userservice.entity.ClientContract;
import tech.torbay.userservice.entity.ClientOrganisation;
import tech.torbay.userservice.entity.ClientTask;
import tech.torbay.userservice.entity.ClientTaskComments;
import tech.torbay.userservice.entity.ClientUser;
import tech.torbay.userservice.entity.ClientUserTasks;
import tech.torbay.userservice.entity.PredefinedTags;
import tech.torbay.userservice.entity.Project;
import tech.torbay.userservice.entity.ProjectReviewRating;
import tech.torbay.userservice.entity.User;
import tech.torbay.userservice.entity.UserWishList;
import tech.torbay.userservice.entity.VendorCategoryRatings;
import tech.torbay.userservice.entity.VendorOrganisation;
import tech.torbay.userservice.entity.VendorProjectInterests;
import tech.torbay.userservice.entity.VendorTags;
import tech.torbay.userservice.entity.VendorUser;
import tech.torbay.userservice.exception.ResourceNotFoundException;
import tech.torbay.userservice.repository.ClientBuildingRepoRepository;
import tech.torbay.userservice.repository.ClientContractRepository;
import tech.torbay.userservice.repository.ClientOrganisationRepository;
import tech.torbay.userservice.repository.ClientTaskCommentsRepository;
import tech.torbay.userservice.repository.ClientTaskRepository;
import tech.torbay.userservice.repository.ClientUserRepository;
import tech.torbay.userservice.repository.ClientUserTasksRepository;
import tech.torbay.userservice.repository.PredefinedTagsRepository;
import tech.torbay.userservice.repository.ProjectRepository;
import tech.torbay.userservice.repository.ProjectReviewRatingRepository;
import tech.torbay.userservice.repository.UserRepository;
import tech.torbay.userservice.repository.UserWishListRepository;
import tech.torbay.userservice.repository.VendorCategoryRatingsRepository;
import tech.torbay.userservice.repository.VendorOrganisationRepository;
import tech.torbay.userservice.repository.VendorTagsRepository;
import tech.torbay.userservice.repository.VendorUserRepository;

@Component
public class SupportUserService {
	
	
	@Autowired
	UserRepository userRepository;
	VendorTagsRepository vendorTagsRepository;
	@Autowired
	ClientOrganisationRepository clientOrganisationRepository;
	@Autowired
	ClientUserRepository clientUserRepository;
	@Autowired
	VendorUserRepository vendorUserRepository;
	
}
