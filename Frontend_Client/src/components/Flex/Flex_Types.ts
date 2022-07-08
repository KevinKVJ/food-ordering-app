import type { CSSProperties, PropsWithChildren } from 'react';

export interface FlexProps extends PropsWithChildren {
    spacing?: 'small' | 'medium' | 'large' | number | [number, number];
    itemStyle?: string | CSSProperties;
    vertical?: boolean;
    wrap?: boolean;
}