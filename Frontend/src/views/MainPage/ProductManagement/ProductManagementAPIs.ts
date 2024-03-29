import API from '@/http/request'
import { productDataInserted } from './ProductDataTypes';

/**
 * API: Get All Products
 */
export const apiGetAllProducts = () => API.get('/api/v1/product/all');

/**
 * API: Get All Products
 */
export const apiGetAProductImgByID = (body: { id: number }) => API.post('/api/v1/product/image', body);
/**
 * API: Get All Products
 */
export const apiGetANoImgProductByID = (body: { id: number }) => API.post('/api/v1/product/image', body);
/**
 * API: Get All Products
 */
export const apiGetProductOptionsByID = (body: { id: number }) => API.post('/api/v1/product/options', body);
/**
 * API: Get All Products
 */
/* TODO 剔除某些Key */
export const apiInsertAProduct = (body: productDataInserted) => API.post('/api/v1/product/insert', body);




/**
 * API: Get All Categories
 */
export const apiGetAllCategoies = () => API.get('/api/v1/merchant/category');


/**
 * API: Get All Products By Category Id
 */
export const apiGetProductsByCategoryId = (body:{ id: number }) => API.post("/api/v1/merchant/category/products",body);
