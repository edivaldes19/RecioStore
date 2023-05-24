package com.edival.reciostore.di

import com.edival.reciostore.domain.repository.AddressRepository
import com.edival.reciostore.domain.repository.AuthRepository
import com.edival.reciostore.domain.repository.CategoriesRepository
import com.edival.reciostore.domain.repository.InfoRepository
import com.edival.reciostore.domain.repository.OrdersRepository
import com.edival.reciostore.domain.repository.ProductsRepository
import com.edival.reciostore.domain.repository.ShoppingBagRepository
import com.edival.reciostore.domain.repository.UsersRepository
import com.edival.reciostore.domain.useCase.address.AddressUseCase
import com.edival.reciostore.domain.useCase.address.CreateAddressUseCase
import com.edival.reciostore.domain.useCase.address.DeleteAddressUseCase
import com.edival.reciostore.domain.useCase.address.GetAddressByUserUseCase
import com.edival.reciostore.domain.useCase.address.UpdateAddressUseCase
import com.edival.reciostore.domain.useCase.auth.*
import com.edival.reciostore.domain.useCase.categories.*
import com.edival.reciostore.domain.useCase.info.GetInfoByKeyUseCase
import com.edival.reciostore.domain.useCase.info.GetInfoUseCase
import com.edival.reciostore.domain.useCase.info.InfoUseCase
import com.edival.reciostore.domain.useCase.orders.CreateOrderUseCase
import com.edival.reciostore.domain.useCase.orders.GetOrdersByClientUseCase
import com.edival.reciostore.domain.useCase.orders.GetOrdersUseCase
import com.edival.reciostore.domain.useCase.orders.OrdersUseCase
import com.edival.reciostore.domain.useCase.orders.UpdateOrderStatusUseCase
import com.edival.reciostore.domain.useCase.products.CreateProductUseCase
import com.edival.reciostore.domain.useCase.products.DeleteProductUseCase
import com.edival.reciostore.domain.useCase.products.DownloadProdImagesUseCase
import com.edival.reciostore.domain.useCase.products.GetProductsByCategoryUseCase
import com.edival.reciostore.domain.useCase.products.GetProductsByNameUseCase
import com.edival.reciostore.domain.useCase.products.GetProductsUseCase
import com.edival.reciostore.domain.useCase.products.ProductsUseCase
import com.edival.reciostore.domain.useCase.products.UpdateProductUseCase
import com.edival.reciostore.domain.useCase.shopping_bag.AddProductBagUseCase
import com.edival.reciostore.domain.useCase.shopping_bag.DeleteProductBagUseCase
import com.edival.reciostore.domain.useCase.shopping_bag.EmptyShoppingBagUseCase
import com.edival.reciostore.domain.useCase.shopping_bag.GetProductBagByIdUseCase
import com.edival.reciostore.domain.useCase.shopping_bag.GetProductsBagUseCase
import com.edival.reciostore.domain.useCase.shopping_bag.ShoppingBagUseCase
import com.edival.reciostore.domain.useCase.users.DownloadUserImgUseCase
import com.edival.reciostore.domain.useCase.users.GetUsersUseCase
import com.edival.reciostore.domain.useCase.users.UpdateUserToAdminUseCase
import com.edival.reciostore.domain.useCase.users.UpdateUserToClientUseCase
import com.edival.reciostore.domain.useCase.users.UpdateUserUseCase
import com.edival.reciostore.domain.useCase.users.UsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideAuthUseCase(authRepository: AuthRepository): AuthUseCase {
        return AuthUseCase(
            createTokenUseCase = CreateTokenUseCase(authRepository),
            signUpUseCase = SignUpUseCase(authRepository),
            loginUseCase = LoginUseCase(authRepository),
            updatePasswordUseCase = UpdatePasswordUseCase(authRepository),
            updateNotificationTokenUseCase = UpdateNotificationTokenUseCase(authRepository),
            deleteAccountUseCase = DeleteAccountUseCase(authRepository),
            saveSessionUseCase = SaveSessionUseCase(authRepository),
            getSessionDataUseCase = GetSessionDataUseCase(authRepository),
            logOutUseCase = LogOutUseCase(authRepository),
            updateSessionUseCase = UpdateSessionUseCase(authRepository)
        )
    }

    @Provides
    fun provideUsersUseCase(usersRepository: UsersRepository): UsersUseCase {
        return UsersUseCase(
            getUsersUseCase = GetUsersUseCase(usersRepository),
            updateUserUseCase = UpdateUserUseCase(usersRepository),
            updateUserToClientUseCase = UpdateUserToClientUseCase(usersRepository),
            updateUserToAdminUseCase = UpdateUserToAdminUseCase(usersRepository),
            downloadUserImgUseCase = DownloadUserImgUseCase(usersRepository)
        )
    }

    @Provides
    fun provideCategoriesUseCase(categoriesRepository: CategoriesRepository): CategoriesUseCase {
        return CategoriesUseCase(
            getCategoriesUseCase = GetCategoriesUseCase(categoriesRepository),
            createCategoryUseCase = CreateCategoryUseCase(categoriesRepository),
            updateCategoryUseCase = UpdateCategoryUseCase(categoriesRepository),
            deleteCategoryUseCase = DeleteCategoryUseCase(categoriesRepository),
            downloadCtgImgUseCase = DownloadCtgImgUseCase(categoriesRepository)
        )
    }

    @Provides
    fun provideProductsUseCase(productsRepository: ProductsRepository): ProductsUseCase {
        return ProductsUseCase(
            getProductsUseCase = GetProductsUseCase(productsRepository),
            getProductsByCategoryUseCase = GetProductsByCategoryUseCase(productsRepository),
            createProductUseCase = CreateProductUseCase(productsRepository),
            updateProductUseCase = UpdateProductUseCase(productsRepository),
            deleteProductUseCase = DeleteProductUseCase(productsRepository),
            getProductsByNameUseCase = GetProductsByNameUseCase(productsRepository),
            downloadProdImagesUseCase = DownloadProdImagesUseCase(productsRepository)
        )
    }

    @Provides
    fun provideShoppingBagUseCase(shoppingBagRepository: ShoppingBagRepository): ShoppingBagUseCase {
        return ShoppingBagUseCase(
            getProductsBagUseCase = GetProductsBagUseCase(shoppingBagRepository),
            getProductBagByIdUseCase = GetProductBagByIdUseCase(shoppingBagRepository),
            addProductBagUseCase = AddProductBagUseCase(shoppingBagRepository),
            deleteProductBagUseCase = DeleteProductBagUseCase(shoppingBagRepository),
            emptyShoppingBagUseCase = EmptyShoppingBagUseCase(shoppingBagRepository)
        )
    }

    @Provides
    fun provideAddressUseCase(addressRepository: AddressRepository): AddressUseCase {
        return AddressUseCase(
            getAddressByUserUseCase = GetAddressByUserUseCase(addressRepository),
            createAddressUseCase = CreateAddressUseCase(addressRepository),
            updateAddressUseCase = UpdateAddressUseCase(addressRepository),
            deleteAddressUseCase = DeleteAddressUseCase(addressRepository)
        )
    }

    @Provides
    fun provideOrdersUseCase(ordersRepository: OrdersRepository): OrdersUseCase {
        return OrdersUseCase(
            getOrdersUseCase = GetOrdersUseCase(ordersRepository),
            getOrdersByClientUseCase = GetOrdersByClientUseCase(ordersRepository),
            createOrderUseCase = CreateOrderUseCase(ordersRepository),
            updateOrderStatusUseCase = UpdateOrderStatusUseCase(ordersRepository)
        )
    }

    @Provides
    fun provideInfoUseCase(infoRepository: InfoRepository): InfoUseCase {
        return InfoUseCase(
            getInfoUseCase = GetInfoUseCase(infoRepository),
            getInfoByKeyUseCase = GetInfoByKeyUseCase(infoRepository)
        )
    }
}