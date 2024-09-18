package br.com.projectbank.pojo

import com.fasterxml.jackson.annotation.JsonProperty

class BalancePojo (
    @JsonProperty("success")
    var success : Boolean,
    @JsonProperty("message")
    var message : String
)