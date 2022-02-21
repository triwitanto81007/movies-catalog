package id.namikaze.moviescatalog.utlis

import id.namikaze.moviescatalog.data.source.remote.response.*
import id.namikaze.moviescatalog.domain.model.*

object DataMapper {

    fun genresMapperResponsesToDomain(input: List<GenreResponse>): List<Genre> {
        val dataList = ArrayList<Genre>()
        input.map {
            val data = Genre(
                id = it.id,
                name = it.name
            )
            dataList.add(data)
        }
        return dataList
    }

    fun moviesMapperResponsesToDomain(input: List<MovieResponse>): List<Movie> {
        val dataList = ArrayList<Movie>()
        input.map {
            var genre = String()
            it.genreIds.map { genreId ->
                if (genre.isEmpty())
                    genre += genreId
                else
                    genre += ",$genreId"
            }

            val data = Movie(
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
            dataList.add(data)
        }
        return dataList
    }

    fun movieDetailMapperResponsesToDomain(input: MovieDetailResponse): MovieDetail {
        return MovieDetail(
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

    fun reviewMapperResponsesToDomain(input: ReviewsResponse): List<Review> {
        val dataList = ArrayList<Review>()
        input.results.map {
            val data = Review(
                id = input.id,
                idAuthor = it.id.toString(),
                author = it.author,
                username = it.authorDetails.username,
                avatarPath = it.authorDetails.avatarPath,
                rating = it.authorDetails.rating,
                content = it.content,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt,
                url = it.url
            )
            dataList.add(data)
        }
        return dataList
    }

    fun trailerMapperResponsesToDomain(input: TrailersResponse): Trailer {
        return Trailer(
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
}