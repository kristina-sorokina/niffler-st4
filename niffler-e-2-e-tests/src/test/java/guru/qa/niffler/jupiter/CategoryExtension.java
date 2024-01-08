package guru.qa.niffler.jupiter;

import guru.qa.niffler.api.CategoryApi;
import guru.qa.niffler.model.CategoryJson;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.support.AnnotationSupport;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CategoryExtension implements BeforeEachCallback {

  public static final ExtensionContext.Namespace NAMESPACE
      = ExtensionContext.Namespace.create(CategoryExtension.class);

  private static final OkHttpClient HTTP_CLIENT = new OkHttpClient.Builder().build();
  private static final Retrofit retrofit = new Retrofit.Builder()
      .client(HTTP_CLIENT)
      .baseUrl("http://127.0.0.1:8093")
      .addConverterFactory(JacksonConverterFactory.create())
      .build();

  private final CategoryApi categoryApi = retrofit.create(CategoryApi.class);

  @Override
  public void beforeEach(ExtensionContext extensionContext) throws Exception {
    Optional<GenerateCategory> category = AnnotationSupport.findAnnotation(
        extensionContext.getRequiredTestMethod(),
            GenerateCategory.class
    );

    if (category.isPresent()) {
      GenerateCategory categoryData = category.get();
      CategoryJson categoryJson = new CategoryJson(
          null,
              categoryData.category(),
              categoryData.username()
      );

      if (isUnique(categoryData.username(), categoryData.category())) {
        CategoryJson created = categoryApi.addCategory(categoryJson).execute().body();
        extensionContext.getStore(NAMESPACE)
                .put("category", created);
      }
    }
  }

  private boolean isUnique(String username, String category) throws IOException {
    List<CategoryJson> categories =  categoryApi.getAllCategoriesByUser(username).execute().body();
    return Objects.requireNonNull(categories).stream()
            .noneMatch(c -> category.equals(c.category()));
  }
}
