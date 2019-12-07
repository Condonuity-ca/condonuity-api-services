package tech.torbay.userservice.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ClientAssociateId
    implements Serializable {
 
    @Column(name = "client_organisation_id")
    private Integer clientOrganisationId;
 
    @Column(name = "client_id")
    private Integer clientId;
 
    private ClientAssociateId() {}
 
    public ClientAssociateId(
        Integer clientOrganisationId,
        Integer clientId) {
        this.clientOrganisationId = clientOrganisationId;
        this.clientId = clientId;
    }
 
    //Getters omitted for brevity
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        ClientAssociateId that = (ClientAssociateId) o;
        return Objects.equals(clientOrganisationId, that.clientOrganisationId) &&
               Objects.equals(clientId, that.clientId);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(clientOrganisationId, clientId);
    }
}
