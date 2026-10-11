module gov.ug.immigration {
    requires gov.ug.nira;
    provides gov.ug.core.api.GovService with gov.ug.immigration.PassportService;
}
