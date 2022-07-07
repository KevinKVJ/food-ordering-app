import { useMemo } from "react";

export const useClassNameStr = (classStringArray:string[]) => {
    return classStringArray.join(' ').trim();
}