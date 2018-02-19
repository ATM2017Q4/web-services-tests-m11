package jsonplaceholdertests.entities;

public class User {
    private int id;
    private String name;
    private String username;
    private String phone;
    private String webSite;
    private Address address;
    private Company company;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebSite() {
        return webSite;
    }

    public Address getAddress() {
        return address;
    }

    public Company getCompany() {
        return company;
    }


    public static class Address {
        private String street;
        private String suite;
        private String city;
        private String zipCode;

        public Geo getGeo() {
            return geo;
        }

        private Geo geo;

        public String getStreet() {
            return street;
        }

        public String getSuite() {
            return suite;
        }

        public String getCity() {
            return city;
        }

        public String getZipCode() {
            return zipCode;
        }

        public static class Geo {
            private String lat;
            private String lng;

            public String getLat() {
                return lat;
            }

            public String getLng() {
                return lng;
            }

        }
    }

    public static class Company {
        private String name;
        private String catchPhrase;
        private String bs;

        public String getName() {
            return name;
        }

        public String getCatchPhrase() {
            return catchPhrase;
        }

        public String getBs() {
            return bs;
        }


    }

}
