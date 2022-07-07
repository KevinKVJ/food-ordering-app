import { useMemo } from 'react';
import type { CSSProperties, PropsWithChildren } from 'react';
import React from 'react';
import styles from './Flex.module.scss';
import parseStyles from '@/utils/parseStyles';

interface FlexProps extends PropsWithChildren {
    spacing?: 'small' | 'medium' | 'large' | number;
    itemStyle?: string | CSSProperties;
    vertical?:boolean
}

// const itemClasses:string = [

// ].join(' ');

const Flex = ({ children, spacing = 'medium', itemStyle }: FlexProps) => {
    const flexSpacing = useMemo(() => {
        let retVal: string | number = spacing;
        switch (spacing) {
            case 'small':
                retVal = '5px';
                break;
            case 'medium':
                retVal = '10px';
                break;
            case 'large':
                retVal = '15px';
                break;
            default:
                break;
        }
        return retVal;
    }, [spacing]);
    const childStyle = useMemo(
        () =>
            typeof itemStyle === 'string' ? parseStyles(itemStyle) : itemStyle,
        [itemStyle]
    );

    return (
        <>
            {/* {console.log(children)} */}
            <div className={`${styles.flexBox}`}>
                {React.Children.toArray(children).map(
                    (child, index, oriChilds) => {
                        // if(index !== oriChilds.length){
                        // }
                        return (
                            <div
                                key={index}
                                className={`666`}
                                style={{
                                    marginRight:
                                        index !== oriChilds.length - 1
                                            ? flexSpacing
                                            : undefined,
                                    ...childStyle,
                                }}>
                                {child}
                            </div>
                        );
                    }
                )}
            </div>
        </>
    );
};

export default Flex;
