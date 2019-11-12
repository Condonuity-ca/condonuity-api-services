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
	private int gym;
	private int partyRoom;
	private int swimPool;
	private int parking;
	private int poolIndoor;
	private int poolOutdoor;
	private int parkingGroundLevel;
	private int parkingUnderGround;
	private int GroundLevelParkingSpots;
	private int UnderGroundParkingSpots;
	private int elevatorsCount;
	private String otherInformation;
	
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
	public int getGym() {
		return gym;
	}
	public void setGym(int gym) {
		this.gym = gym;
	}
	public int getPartyRoom() {
		return partyRoom;
	}
	public void setPartyRoom(int partyRoom) {
		this.partyRoom = partyRoom;
	}
	public int getSwimPool() {
		return swimPool;
	}
	public void setSwimPool(int swimPool) {
		this.swimPool = swimPool;
	}
	public int getParking() {
		return parking;
	}
	public void setParking(int parking) {
		this.parking = parking;
	}
	public int getPoolIndoor() {
		return poolIndoor;
	}
	public void setPoolIndoor(int poolIndoor) {
		this.poolIndoor = poolIndoor;
	}
	public int getPoolOutdoor() {
		return poolOutdoor;
	}
	public void setPoolOutdoor(int poolOutdoor) {
		this.poolOutdoor = poolOutdoor;
	}
	public int getParkingGroundLevel() {
		return parkingGroundLevel;
	}
	public void setParkingGroundLevel(int parkingGroundLevel) {
		this.parkingGroundLevel = parkingGroundLevel;
	}
	public int getParkingUnderGround() {
		return parkingUnderGround;
	}
	public void setParkingUnderGround(int parkingUnderGround) {
		this.parkingUnderGround = parkingUnderGround;
	}
	public int getGroundLevelParkingSpots() {
		return GroundLevelParkingSpots;
	}
	public void setGroundLevelParkingSpots(int groundLevelParkingSpots) {
		GroundLevelParkingSpots = groundLevelParkingSpots;
	}
	public int getUnderGroundParkingSpots() {
		return UnderGroundParkingSpots;
	}
	public void setUnderGroundParkingSpots(int underGroundParkingSpots) {
		UnderGroundParkingSpots = underGroundParkingSpots;
	}
	public int getElevatorsCount() {
		return elevatorsCount;
	}
	public void setElevatorsCount(int elevatorsCount) {
		this.elevatorsCount = elevatorsCount;
	}
	public String getOtherInformation() {
		return otherInformation;
	}
	public void setOtherInformation(String otherInformation) {
		this.otherInformation = otherInformation;
	}
	@Override
	public String toString() {
		return "ClientAmenities [id=" + id + ", organisationId=" + organisationId + ", gym=" + gym + ", partyRoom="
				+ partyRoom + ", swimPool=" + swimPool + ", parking=" + parking + ", poolIndoor=" + poolIndoor
				+ ", poolOutdoor=" + poolOutdoor + ", parkingGroundLevel=" + parkingGroundLevel
				+ ", parkingUnderGround=" + parkingUnderGround + ", GroundLevelParkingSpots=" + GroundLevelParkingSpots
				+ ", UnderGroundParkingSpots=" + UnderGroundParkingSpots + ", elevatorsCount=" + elevatorsCount
				+ ", otherInformation=" + otherInformation + "]";
	}
	

}
