export const errorCode = (code:string):string => {   
    let msg = ""
    switch (code) {
        case "1001":
            msg = "LALALA!"
            break;
        case "1002":
            msg = "XIXIXI!"
            break;
        default:
            break;
    }
    return msg;
}