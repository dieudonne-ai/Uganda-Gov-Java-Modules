module gov.ug.nira {
    requires transitive gov.ug.core;
    exports gov.ug.nira.api;
    exports gov.ug.nira.records to gov.ug.immigration;
    provides gov.ug.core.api.GovService with gov.ug.nira.api.NiraService;
}
