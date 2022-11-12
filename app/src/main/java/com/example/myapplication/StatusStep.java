package com.example.myapplication;

public class StatusStep {

private String id;
private String status;
private String info;
private String updated_at;
private String step_status;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public String getInfo() {
return info;
}

public void setInfo(String info) {
this.info = info;
}

public String getUpdatedAt() {
return updated_at;
}

public void setUpdatedAt(String updatedAt) {
this.updated_at = updatedAt;
}

public String getStepStatus() {
return step_status;
}

public void setStepStatus(String stepStatus) {
this.step_status= stepStatus;
}

}