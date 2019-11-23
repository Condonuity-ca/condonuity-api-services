package tech.torbay.securityservice.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "client_organisation_amenities")
public class ClientAmenities {
	
	public ClientAmenities() {
	
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)  
	private int id = 0;
	
	@ManyToOne
    @JoinColumn(name = "client_organisation_id")
	private ClientOrganisation clientOrganisation;
	
//	private int clientOrganisationId = 0;
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
	public int getClientOrganisationId() {
		return clientOrganisation.getClientOrganisationId();
	}
	public void setClientOrganisationId(int clientOrganisationId) {
		this.clientOrganisation.setClientOrganisationId(clientOrganisationId);
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
		return "ClientAmenities [id=" + id
				+ /* ", clientOrganisationId=" + clientOrganisationId + */ ", amenities_name=" + amenities_name
				+ ", amenities_type=" + amenities_type + ", amenities_count=" + amenities_count + ", created_at="
				+ created_at + ", modified_date=" + modified_date + "]";
	}
	
	
}
