package resources;

import Pojo.AddPlace;
import Pojo.AddPlaceLocation;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuilder {

    public AddPlace addPlaceData(String name, String language, String address){

        AddPlace addPlace = new AddPlace();
        AddPlaceLocation addPlaceLocation = new AddPlaceLocation();
        addPlaceLocation.setLat(-38.383494);
        addPlaceLocation.setLng(33.427362);
        addPlace.setLocation(addPlaceLocation);
        addPlace.setAccuracy(50);
        addPlace.setName(name);
        addPlace.setPhone_number("(+91) 983 893 3937");
        addPlace.setAddress(address);
        List<String> list = new ArrayList<String>();
        list.add("shoe park");
        list.add("shop");
        addPlace.setTypes(list);
        addPlace.setWebsite("http://google.com");
        addPlace.setLanguage(language);

        return addPlace;
    }

    public String deletePlacePayLoad(String placeId){

        return "\"place_id\":\""+placeId+"\"";
    }
}
