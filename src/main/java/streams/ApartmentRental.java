package streams;

import java.util.ArrayList;
import java.util.List;

public class ApartmentRental {

    private List<Apartment> apartmentRental = new ArrayList<>();

    public void addApartment(Apartment apartment) {
        apartmentRental.add(apartment);
    }

    public List<Apartment> findApartmentByLocation(String location) {
        return apartmentRental.stream()
                .filter(apartment -> location.equals(apartment.getLocation())).toList();
    }

    public List<Apartment> findApartmentByExtras(String... extras) {
        return apartmentRental.stream()
                .filter(apartment -> apartment.getExtras().containsAll(List.of(extras))).toList();
    }

    public boolean isThereApartmentWithBathroomType(BathRoomType bathRoomType) {
        return apartmentRental.stream()
                .anyMatch(apartment -> bathRoomType == apartment.getBathRoomType());
    }

    public List<Integer> findApartmentsSize() {
        return apartmentRental.stream().map(Apartment::getSize).toList();
    }
}
