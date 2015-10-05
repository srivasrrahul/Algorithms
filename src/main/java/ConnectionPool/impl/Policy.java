package ConnectionPool.impl;


import ConnectionPool.*;

class Policy {
    static boolean validate(ConfigParam configParam) {
        //validate logic throw exception
        if (configParam.getMin() > configParam.getMax()) {
            System.out.println("Min is greater than max");
            return false;
        }

        //Any other validation

        return true;
    }

}
