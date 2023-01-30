import 'slick-carousel/slick/slick-theme.scss';
import 'slick-carousel/slick/slick.scss';

import type { ForwardRefRenderFunction } from 'react';
import { forwardRef, PropsWithChildren, useImperativeHandle, useMemo, useRef } from 'react';
import type { Settings } from 'react-slick';
import Slider from 'react-slick';

/* Really appreciate the react-slick dev group */
interface SwiperProps extends PropsWithChildren<Settings> {
    currentSlide: number;
}

interface refObjectTypes {
    prevSlide: () => void;
    nextSlide: () => void;
}

// const Swiper = forwardRef<Slider,SwiperProps>(({ currentSlide = 0,children, ...swiperSettings }:SwiperProps,ref) => {
//     const swiper_ref = useRef<Slider>(null);

//     useEffect(() => {
//       swiper_ref.current?.slickGoTo(currentSlide,true)

//     //   return () => {
//     //     second
//     //   }
//     }, [currentSlide])

//     const settings = useMemo(
//         () => ({
//             dots: true,
//             speed:500,
//             // infinite: true,
//             // slidesToShow: 3,
//             // slidesToScroll: 3,
//             ...swiperSettings,
//         }),
//         [swiperSettings]
//     );
//     return (
//         <div>
//             {/* <h2> Single Item</h2> */}
//             <Slider {...settings} ref={swiper_ref}>{children}</Slider>
//         </div>
//     );
// });
const Swiper: ForwardRefRenderFunction<refObjectTypes, SwiperProps> = ({ currentSlide, children, ...swiperSettings }, onRef) => {
    const swiperRef = useRef<Slider>(null);

    useImperativeHandle(onRef, () => ({
        prevSlide() {
            swiperRef.current?.slickPrev();
        },
        nextSlide() {
            swiperRef.current?.slickNext();
        },
    }));

    const settings = useMemo<Settings>(
        () => ({
            dots: true,
            speed: 500,
            afterChange: curr => {
                console.log(`Current Slide`, Math.ceil(curr / 3));
                console.log(`CurrentIndex`, curr);
            },
            ...swiperSettings,
            // infinite: true,
            // slidesToShow: 3,
            // slidesToScroll: 3,
        }),
        [swiperSettings]
    );
    return (
        <div>
            <Slider {...settings} ref={swiperRef}>
                {children}
            </Slider>
        </div>
    );
};

export default forwardRef(Swiper);
