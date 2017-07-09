package administration.jpa.daos;

import model.challenges.Challenge;
import model.profile.UserData;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
    This class is used to create Dao instances and to hide all
    the database related stuff. Therefore, it can be considered
    as a Facade to the database module/package.

    It is implemented as a singleton and holds a reference to the
    sole instance of our EntityManagerFactory that manages our
    persistence context.
 */
public class DataController
{
    private static final String PERSISTENCE_UNIT_NAME = "addressbook-unit";

    private EntityManagerFactory entityManagerFactory;

    /*
        Singleton definition
     */
    private static DataController instance;

    public static DataController getInstance()
    {
        if( instance == null )
            instance = new DataController();

        return instance;
    }


    private DataController()
    {
        this.entityManagerFactory = Persistence.createEntityManagerFactory( PERSISTENCE_UNIT_NAME );
    }

    /*
        Dao Getter
     */

    public IGenericDao<Challenge> getChallengeDao()
    {
        return new GenericDao<Challenge>( Challenge.class,
                this.entityManagerFactory.createEntityManager() );
    }

    public IGenericDao<UserData> getAddressDao()
    {
        return new GenericDao<UserData>( UserData.class,
                this.entityManagerFactory.createEntityManager() );
    }

}