module gov.ug.nira {
    requires transitive gov.ug.core;
    exports gov.ug.nira.api;
    provides gov.ug.core.api.GovService with gov.ug.nira.api.NiraService;
}
