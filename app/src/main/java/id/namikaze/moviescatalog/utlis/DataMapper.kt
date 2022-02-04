package id.namikaze.moviescatalog.utlis

import id.namikaze.moviescatalog.data.source.local.entity.*
import id.namikaze.moviescatalog.data.source.remote.response.*
import id.namikaze.moviescatalog.domain.model.*

object DataMapper {

    object GenresMapper {
        fun mapResponsesToEntities(input: List<GenreResponse>): List<GenreEntity> {
            val dataList = ArrayList<GenreEntity>()
            input.map {
                val data = GenreEntity(
                    id = it.id,
                    name = it.name
                )
                dataList.add(data)
            }
            return dataList
        }

        fun mapEntitiesToDomain(input: List<GenreEntity>): List<Genre> {
            return input.map {
                Genre(
                    id = it.id,
                    name = it.name
                )
            }
        }
    }

    object MoviesMapper {
        fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
            val dataList = ArrayList<MovieEntity>()
            input.map {
                var genre = String()
                it.genreIds.map { genreId ->
                    if (genre.isEmpty())
                        genre += genreId
                    else
                        genre += ",$genreId"
                }

                val data = MovieEntity(
                    id = it.id,
                    adult = it.adult,
                    backdropPath = it.backdropPath,
                    genreIds = genre,
                    originalLanguage = it.originalLanguage,
                    originalTitle = it.originalTitle,
                    overview = it.overview,
                    popularity = it.popularity,
                    posterPath = it.posterPath,
                    releaseDate = it.releaseDate,
                    title = it.title,
                    video = it.video,
                    voteAverage = it.voteAverage,
                    voteCount = it.voteCount

                    )
                dataList.add(data)
            }
            return dataList
        }

        fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> {
            return input.map {
                Movie(
                    id = it.id,
                    adult = it.adult,
                    backdropPath = it.backdropPath,
                    originalLanguage = it.originalLanguage,
                    originalTitle = it.originalTitle,
                    overview = it.overview,
                    popularity = it.popularity,
                    posterPath = it.posterPath,
                    releaseDate = it.releaseDate,
                    title = it.title,
                    video = it.video,
                    voteAverage = it.voteAverage,
                    voteCount = it.voteCount
                )
            }
        }
    }

    object MovieDetailMapper {
        fun mapResponsesToEntities(input: MovieDetailResponse): MovieDetailEntity {
            return MovieDetailEntity(
                id = input.id,
                adult = input.adult,
                backdropPath = input.backdropPath,
                originalLanguage = input.originalLanguage,
                originalTitle = input.originalTitle,
                overview = input.overview,
                posterPath = input.posterPath,
                releaseDate = input.releaseDate,
                title = input.title,
                video = input.video,
                voteAverage = input.voteAverage,
                voteCount = input.voteCount

            )
        }
        fun mapEntitiesToDomain(input: MovieDetailEntity?): MovieDetail {
            return MovieDetail(
                id = input?.id,
                adult = input?.adult,
                backdropPath = input?.backdropPath,
                originalLanguage = input?.originalLanguage,
                originalTitle = input?.originalTitle,
                overview = input?.overview,
                posterPath = input?.posterPath,
                releaseDate = input?.releaseDate,
                title = input?.title,
                video = input?.video,
                voteAverage = input?.voteAverage,
                voteCount = input?.voteCount
            )
        }
    }

    object ReviewMapper {

        fun mapResponsesToEntities(input: ReviewsResponse): List<ReviewEntity> {
            val dataList = ArrayList<ReviewEntity>()
            input.results.map {
                val data = ReviewEntity(
                    id = input.id,
                    idAuthor = it.id.toString(),
                    author = it.author,
                    username = it.authorDetails?.username,
                    avatarPath = it.authorDetails?.avatarPath,
                    rating = it.authorDetails?.rating,
                    content = it.content,
                    createdAt = it.createdAt,
                    updatedAt = it.updatedAt,
                    url = it.url
                )
                dataList.add(data)
            }
            return dataList
        }

        fun mapEntitiesToDomain(input: List<ReviewEntity>): List<Review> {
            return input.map {
                Review(
                    id = it.id,
                    idAuthor = it.idAuthor,
                    author = it.author,
                    username = it.username,
                    avatarPath = it.avatarPath,
                    rating = it.rating,
                    content = it.content,
                    createdAt = it.createdAt,
                    updatedAt = it.updatedAt,
                    url = it.url
                )
            }
        }
    }

    object TrailerMapper {
        fun mapResponsesToEntities(input: TrailersResponse): TrailerEntity {
            return TrailerEntity(
                id = input.id,
                name = input.results[0].name,
                key = input.results[0].key,
                site = input.results[0].site,
                size = input.results[0].size,
                type = input.results[0].type,
                official = input.results[0].official,
                publishedAt = input.results[0].publishedAt

            )
        }
        fun mapEntitiesToDomain(input: TrailerEntity?): Trailer {
            return Trailer(
                id = input?.id,
                name = input?.name,
                key = input?.key,
                site = input?.site,
                size = input?.size,
                type = input?.type,
                official = input?.official,
                publishedAt = input?.publishedAt,
            )
        }
    }

}