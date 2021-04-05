package ioannis.visvikis.issuetracker.demo.security;

public enum SecurityEnum {

    API_SECRET_KEY("IssueTrackerAppSecretKey"),
    TOKEN_VALIDITY_PERIOD_MILLISECONDS_STRING("1800000"), //30 minutes
    CLAIM_USERNAME("user_name"); //Fifteen minutes

    private String enumValue;


    public String getEnumValue() {
        return enumValue;
    }

    private SecurityEnum(String value) {
        this.enumValue = value;
    }

}
