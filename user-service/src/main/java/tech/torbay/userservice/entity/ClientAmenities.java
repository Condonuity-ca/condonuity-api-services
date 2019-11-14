package tech.torbay.userservice.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "organisation_amenities_info")
public class ClientAmenities {
	
	public ClientAmenities() {
	
	}
	@Id
	private int id;
	private int organisationId;
	private String amenities_name;
	private String amenities_type;
	private int amenities_count;
	private String created_at;
	private String modified_date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrganisationId() {
		return organisationId;
	}
	public void setOrganisationId(int organisationId) {
		this.organisationId = organisationId;
	}
	public String getAmenities_name() {
		return amenities_name;
	}
	public void setAmenities_name(String amenities_name) {
		this.amenities_name = amenities_name;
	}
	public String getAmenities_type() {
		return amenities_type;
	}
	public void setAmenities_type(String amenities_type) {
		this.amenities_type = amenities_type;
	}
	public int getAmenities_count() {
		return amenities_count;
	}
	public void setAmenities_count(int amenities_count) {
		this.amenities_count = amenities_count;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getModified_date() {
		return modified_date;
	}
	public void setModified_date(String modified_date) {
		this.modified_date = modified_date;
	}
	@Override
	public String toString() {
		return "ClientAmenities [id=" + id + ", organisationId=" + organisationId + ", amenities_name=" + amenities_name
				+ ", amenities_type=" + amenities_type + ", amenities_count=" + amenities_count + ", created_at="
				+ created_at + ", modified_date=" + modified_date + "]";
	}
	
	
}
