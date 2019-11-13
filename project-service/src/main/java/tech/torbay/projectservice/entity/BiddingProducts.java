package tech.torbay.projectservice.entity;

import javax.persistence.Table;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@Table(name = "bidding_products")
public class BiddingProducts {

    public BiddingProducts() {

    }
 
    @Id
	private Integer Id = 0;
	private Integer productType = 0;
    private Integer projectId = 0;
    private Integer biddingId = 0;
    private Integer vendorId = 0;
    private String description;
    private Integer quantity = 0;
    private String unit;
    private String price;
    private String createdDate;
    private String modifiedDate;
    
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public Integer getProductType() {
		return productType;
	}
	public void setProductType(Integer productType) {
		this.productType = productType;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Integer getBiddingId() {
		return biddingId;
	}
	public void setBiddingId(Integer biddingId) {
		this.biddingId = biddingId;
	}
	public Integer getVendorId() {
		return vendorId;
	}
	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	@Override
	public String toString() {
		return "BiddingProducts [Id=" + Id + ", productType=" + productType + ", projectId=" + projectId
				+ ", biddingId=" + biddingId + ", vendorId=" + vendorId + ", description=" + description + ", quantity="
				+ quantity + ", unit=" + unit + ", price=" + price + ", createdDate=" + createdDate + ", modifiedDate="
				+ modifiedDate + "]";
	}
    
}