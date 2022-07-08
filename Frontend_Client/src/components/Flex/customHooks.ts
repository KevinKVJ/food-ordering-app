import { CSSProperties, useMemo } from "react";
import useParseStyles from '@/utils/parseStyles';

export const useClassNameStr = (classStringArray: string[]) => {
    return classStringArray.join(' ').trim();
}

// const flexSpacingStrategies = {
//     NumVal:(spacing:number) => String(spacing),
//     Row_Col:(spacing:[number,number]) => spacing.map(val => `${val}px`).join(' '),
//     [/[abc]/]:(spacing:string) => spacing
// }

export const useFlexSpacings = (spacing: 'small' | 'medium' | 'large' | number | [number, number]) => useMemo(() => {
    // const spacingVal = String(spacing).split(",").join(" ").trim();
    let retVal: [string, string];
    switch (spacing) {
        case 'small':
            retVal = ['10', '10']
            break;
        case 'medium':
            retVal = ['20', '20']
            break;
        case 'large':
            retVal = ['30', '30']
            break;
        default:
            const spacVal = String(spacing).split(",");
            retVal = (spacVal.length === 1 ? [spacVal[0], spacVal[0]] : spacVal) as [string, string]
            break;
    }
    return retVal;
}, [spacing])

export const useItemStyle = (itemStyle: string | CSSProperties | undefined) => useMemo(
    () =>
        typeof itemStyle === 'string'
            ? useParseStyles(itemStyle)
            : itemStyle,
    [itemStyle]
);