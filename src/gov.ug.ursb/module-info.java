module gov.ug.ursb {
    requires gov.ug.nira;
    requires gov.ug.ura;
    provides gov.ug.core.api.GovService with gov.ug.ursb.BusinessRegistrationService;
}
