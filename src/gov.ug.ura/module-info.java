module gov.ug.ura {
    requires gov.ug.nira;
    exports gov.ug.ura.api;
    provides gov.ug.core.api.GovService with gov.ug.ura.api.TaxService;
}
