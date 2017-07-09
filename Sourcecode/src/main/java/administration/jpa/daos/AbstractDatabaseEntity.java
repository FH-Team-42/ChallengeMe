package administration.jpa.daos;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Max on 28.05.2017.
 */


@Entity
@Inheritance( strategy = InheritanceType.TABLE_PER_CLASS )
public abstract class AbstractDatabaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Version
    private Long version;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;

    public AbstractDatabaseEntity() {}

    public Date getCreated()
    {
        return created;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Date getModified()
    {
        return modified;
    }

    public void setModified(Date modified)
    {
        this.modified = modified;
    }

    public Long getVersion()
    {
        return version;
    }

    public void setVersion(Long version)
    {
        this.version = version;
    }


    /*
        JPA Helper Methods
     */

    @PrePersist
    void onCreate()
    {
        System.out.println( "OnCreate" );
        this.setCreated( new Date() );
    }

    @PreUpdate
    void onUpdate()
    {
        System.out.println( "OnUpdate" );
        this.setModified( new Date() );
    }

    @Override
    public String toString() {
        return "AbstractDatabaseEntity{" +
                "id=" + id +
                ", created=" + created +
                ", version=" + version +
                ", modified=" + modified +
                '}';
    }


}
