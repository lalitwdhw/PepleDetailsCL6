package com.cl.peopledetailscl6;

import com.cl.peopledetailscl6.model.User;

public interface DataInterfaceUserPosition {
    /**
     * interface method to pass the selected user data from people recycler adapter to main activity
     * @param user
     */
    void userSelected(User user);
}
