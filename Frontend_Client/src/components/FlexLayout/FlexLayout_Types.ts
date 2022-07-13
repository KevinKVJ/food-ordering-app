import type { CSSProperties, PropsWithChildren } from 'react';

export interface FlexProps extends PropsWithChildren {
    className? : string | undefined
    spacing?: 'small' | 'medium' | 'large' | number | [number, number];
    itemStyle?: string | CSSProperties;
    vertical?: boolean;
    wrap?: boolean;
    justifyContent?: 'flex-start' | 'flex-end' | 'center' | 'space-around' | 'space-between' | 'space-evenly'
    alignItems?: 'flex-start' | 'flex-end' | 'center' | 'baseline' | 'stretch';
}